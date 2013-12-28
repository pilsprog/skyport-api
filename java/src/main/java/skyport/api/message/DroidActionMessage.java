package skyport.api.message;

import java.util.List;

import skyport.api.game.Direction;

public class DroidActionMessage extends ActionMessage {
    @SuppressWarnings("unused")
    private List<Direction> sequence;

    public DroidActionMessage(List<Direction> sequence) {
        this.type = "droid";
        this.sequence = sequence;
    }
}
