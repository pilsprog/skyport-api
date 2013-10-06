package skyport.api.message;

import skyport.api.game.Direction;

public class LaserAttackMessage extends ActionMessage {
    	private Direction direction;
    	public LaserAttackMessage(Direction direction) {
    	    this.type = "laser";
    	    this.direction = direction;
    	}
    	
    @SuppressWarnings("unused")
}
