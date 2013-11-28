package skyport.api.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.TreeMap;

import com.google.common.collect.Lists;

public class BFSIterator implements Iterator<Point> {
	
	protected Map map;
	private Queue<Point> queue;
	private TreeMap<Point,BFSPointData> pathData;
	private Point from;
	protected Point current;

	public BFSIterator(Map map,Point from) {
		super();
		this.map = map;
		this.from = from;
		
		this.queue = new LinkedList<Point>();
		this.pathData = new TreeMap<Point,BFSPointData>();
		
		this.queue.add(from);
		this.pathData.put(from, new BFSPointData(Direction.none,0,null));
	}

	@Override
	public boolean hasNext() {
		return !queue.isEmpty();
	}
	
	public int getNextDistance(){
		return pathData.get(queue.peek()).getLength();
	}
	
	public List<Direction> getPath(Point to){
		if(!pathData.containsKey(to)){
			throw new NoSuchElementException("Tried to get the path to an element without a path to it.");
		}
		return reconstructPath(from,to);
	}
	
	private List<Direction> reconstructPath( Point from, Point to) {
		
		List<Direction> path = new ArrayList<Direction>();
		while(!to.equals(from)){
			BFSPointData data = pathData.get(to);
			path.add(data.getDir());
			to = data.getFrom();
		}
		return Lists.reverse(path);
	}

	@Override
	public Point next() {
		
		if(queue.isEmpty()){
			throw new NoSuchElementException();
		}
		
		current = queue.poll();
		BFSPointData pointData = pathData.get(current);
		
		for(Point p : map.neighbors(current)){
			if(!pathData.containsKey(p) && !p.equals(current)){
				pathData.put(p,new BFSPointData(current.direction(p),pointData.getLength()+1,current));
				queue.add(p);
			}
		}
		return current;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	
	private class BFSPointData {
		private Direction dir;
		private int length;
		private Point from;
		
		public Point getFrom() {
			return from;
		}


		public BFSPointData(Direction dir, int length,Point from) {
			this.dir = dir;
			this.length = length;
			this.from = from;
		}
		

		public Direction getDir() {
			return dir;
		}

		public int getLength() {
			return length;
		}
		
	}

}
