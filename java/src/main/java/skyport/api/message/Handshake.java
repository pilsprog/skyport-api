package skyport.api.message;

public class Handshake extends Message {
    
    private String message = "connect";
    private int revision = 1;
    private String name;
    
    public Handshake(String name) {
        this.name = name;
    }

    public String getName() {
	return name;
    }
}
