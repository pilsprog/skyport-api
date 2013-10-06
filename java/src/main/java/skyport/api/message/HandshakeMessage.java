package skyport.api.message;

public class HandshakeMessage extends Message {
    @SuppressWarnings("unused")
    private int revision = 1;
    private String name;
    
    public HandshakeMessage(String name) {
	this.message = "connect";
        this.name = name;
    }

    public String getName() {
	return name;
    }
}
