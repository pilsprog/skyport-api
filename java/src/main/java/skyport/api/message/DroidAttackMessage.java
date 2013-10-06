package skyport.api.message;

import java.util.List;

import skyport.api.game.Direction;

public class DroidAttackMessage extends ActionMessage {
    @SuppressWarnings("unused")
    private List<Direction> sequence;

    public DroidAttackMessage(List<Direction> sequence) {
        this.type = "droid";
        this.sequence = sequence;
    }
}
