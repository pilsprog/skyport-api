package skyport.api.message;

public class UpgradeMessage extends ActionMessage {
    private String weapon;
    public UpgradeMessage(String weapon) {
	this.type = "upgrade";
	this.weapon = weapon;
    }
}
