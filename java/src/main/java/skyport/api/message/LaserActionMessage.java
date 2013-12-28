package skyport.api.message;

import skyport.api.game.Direction;

public class LaserActionMessage extends ActionMessage {
    @SuppressWarnings("unused")
    private Direction direction;

    public LaserActionMessage(Direction direction) {
        this.type = "laser";
        this.direction = direction;
    }

}
