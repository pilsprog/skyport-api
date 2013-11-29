package skyport.api.game.weapon;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import skyport.api.SkyportClient;
import skyport.api.game.Action;
import skyport.api.game.Direction;
import skyport.api.game.Map;
import skyport.api.game.Point;
import skyport.api.game.Tile;

public class LazerShootIterator implements ShootActionIterator {
	
	private int maxDistance;
	private Point from;
	private Map map;
	private Direction nextDirection;
	private int jOffset;
	private int kOffset;
	
	private Direction currDirection;
	private Point currPoint;
	
	

	public LazerShootIterator(int maxDistance, Point from, Map map) {
		super();
		this.maxDistance = maxDistance;
		this.from = from;
		this.currPoint = from;
		this.map = map;
		
		this.jOffset = 0;
		this.kOffset = 0;
		this.nextDirection = Direction.down;
		
		updateNext();
	}

	@Override
	public boolean hasNext() {
		return !nextDirection.equals(Direction.none);
	}
	
	private void updateNext(){
		updateOffsets();
		if (map.getData(currPoint).equals(Tile.ROCK)
		    || from.distance(currPoint) >= maxDistance) {
			newDirection();
			this.jOffset = 0;
			this.kOffset = 0;
			if(!hasNext())
				return;
			updateNext();
		}
	}
	
	private void updateOffsets(){
		kOffset = updateKOffset(kOffset,nextDirection);
		jOffset = updateJOffset(jOffset,nextDirection);
	}
	
	private int updateJOffset(int jIndex,Direction dir){
		switch(dir){
		case down:
			return ++jIndex;
		case leftDown:
			return ++jIndex;
		case rightUp:
			return --jIndex;
		case up:
			return --jIndex;
		default:
			return jIndex;
		}
	}
	
	private int updateKOffset(int kIndex,Direction dir){
		switch(dir){
		case down:
			return ++kIndex;
		case leftUp:
			return --kIndex;
		case rightDown:
			return ++kIndex;
		case up:
			return --kIndex;
		default:
			return kIndex;
		}
	}
	
	private void newDirection(){
		switch(nextDirection){
		case down:
			nextDirection = Direction.leftDown;
			break;
		case leftDown:
			nextDirection = Direction.leftUp;
			break;
		case leftUp:
			nextDirection = Direction.rightDown;
			break;
		case rightDown:
			nextDirection = Direction.rightUp;
			break;
		case rightUp:
			nextDirection = Direction.up;
			break;
		case up:
			nextDirection = Direction.none;
			break;
		case none:
			assert false;
			break;
		default:
			assert false;
			break;
			
		}
	}

	@Override
	public Point next() {
		if(!hasNext())
			throw new NoSuchElementException();
		
		currPoint = new Point(from.getJ() + jOffset, from.getK() + kOffset);
		currDirection = nextDirection;
		
		updateNext();
		
		return currPoint;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Action getCurrentAction() {
		return new LazerShootAction(currDirection);
	}

	@Override
	public List<Point> goingToHit() {
		List<Point> toRet = new ArrayList<Point>();
		
		int currK = from.getK();
		int currJ = from.getJ();
		
		for(int i = 0; i < maxDistance;i++){
			currK = updateKOffset(currK,currDirection);
			currJ = updateJOffset(currJ,currDirection);
			Point currPoint = new Point(currJ,currK);
			if(map.getData(currPoint).equals(Tile.ROCK)){
				break;	
			}
			toRet.add(new Point(currJ, currK));
		}
		return toRet;
	}
	
	private class LazerShootAction implements Action{
		public Direction shootAt;

		public LazerShootAction(Direction shootAt) {
			super();
			this.shootAt = shootAt;
		}

		@Override
		public void perform(SkyportClient client) {
			client.fireLaser(shootAt);
		}
	}
}
