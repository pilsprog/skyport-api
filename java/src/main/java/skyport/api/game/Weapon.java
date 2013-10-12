package skyport.api.game;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * A weapon in skyport.
 * @author Bjarte
 */
public class Weapon {
    private int level;
    private WeaponType name;

    /**
     * @return The name of the weapon. 
     */
    public WeaponType getName() {
        return name;
    }
    
    /**
     * @param p The point to shoot from.
     * @param m The map the weapon is fired from.
     * @return The points the weapon can shoot at.
     */
    public List<Point> inRange(Point p, Map m){
    	List<Point> toReturn = new ArrayList<Point>();
    	
    	switch(name) {
    	case MORTAR:
    		for(int j = -(2 + level); j < 2 + level;j++){
        		for(int k = -(2 + level); k < 2 + level;k++){
        			int newK = p.getK() + k;
        			int newJ = p.getJ() + j;
        			if(newK < m.getkLength() && newK >= 0 
        			    && newJ < m.getjLength() && newJ >= 0){
        				toReturn.add(new Point(newK,newJ));
        			}
        		}
    		}
    		break;
    	case LASER:
    		for(int j = -(5 + level); j < 5 + level;j++){
        			int newK = p.getK();
        			int newJ = p.getJ() + j;
        			if(newK < m.getkLength() && newK >= 0 
        			    && newJ < m.getjLength() && newJ >= 0){
        				toReturn.add(new Point(newK,newJ));
        			}
    		}
    		for(int k = -(5 + level); k < 5 + level;k++){
    			int newK = p.getK();
    			int newJ = p.getJ() + k;
    			if(newK < m.getkLength() && newK >= 0 
    			    && newJ < m.getjLength() && newJ >= 0){
    				toReturn.add(new Point(newK,newJ));
    			}
    		}
    		for(int i = -(5 + level); i < 5 + level;i++){
    			int newK = p.getK() + i;
    			int newJ = p.getJ() + i;
    			if(newK < m.getkLength() && newK >= 0 
    			    && newJ < m.getjLength() && newJ >= 0){
    				toReturn.add(new Point(newK,newJ));
    			}
    		}
    		break;
    	case DROID:
    		for(int j = -(3 + level); j < 3 + level;j++){
        		for(int k = -(3 + level); k < 3 + level;k++){
        			int newK = p.getK() + k;
        			int newJ = p.getJ() + j;
        			if(newK < m.getkLength() && newK >= 0 
        			    && newJ < m.getjLength() && newJ >= 0){
        				toReturn.add(new Point(newK,newJ));
        			}
        		}
    		}
    		break;
    	default:
    		assert false;
    	}
    	return toReturn;
    }

    /**
     * @return The weapons level.
     */
    public int getLevel() {
        return level;
    }
}
