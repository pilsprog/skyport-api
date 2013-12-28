package skyport.api.message;

public class MortarActionMessage extends ActionMessage {
    @SuppressWarnings("unused")
    private String coordinates;

    public MortarActionMessage(int k, int j) {
        this.type = "mortar";
        this.coordinates = k + "," + j;
    }
}
