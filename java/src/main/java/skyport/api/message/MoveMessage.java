package skyport.api.message;

import skyport.api.game.Direction;

public class MoveMessage extends ActionMessage {
    @SuppressWarnings("unused")
    private Direction  direction;
    

    public MoveMessage(Direction direction) {
        this.type = "move";
        this.direction = direction;
    }
}
