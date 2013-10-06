package skyport.api.message;

public class MortarAttackMessage extends ActionMessage {
    private String coordinates;
    public MortarAttackMessage(int k, int j) {
	this.type = "mortar";
	this.coordinates = k + "," + j;
    }
}
