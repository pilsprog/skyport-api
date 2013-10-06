package skyport.api.message;

public abstract class ActionMessage extends Message {
    protected String type;

    public ActionMessage() {
        this.message = "action";
    }
}
