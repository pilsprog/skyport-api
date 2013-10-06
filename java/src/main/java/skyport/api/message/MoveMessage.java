package skyport.api.message;

import skyport.api.game.Direction;

public class MoveMessage extends ActionMessage {
    Direction direction;
    public MoveMessage(Direction direction) {
	this.type = "move";
	this.direction = direction;
    }
}
