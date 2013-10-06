package skyport.api.message;

import java.util.List;

import skyport.api.game.Direction;

public class DroidAttackMessage extends ActionMessage {
    private List<Direction> sequence;
    
    public DroidAttackMessage(List<Direction> sequence) {
	this.type = "droid";
	this.sequence = sequence;
    }
}
