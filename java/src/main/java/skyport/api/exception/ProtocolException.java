package skyport.api.exception;

public class ProtocolException extends RuntimeException {
    private static final long serialVersionUID = 4551849434956754023L;
    public ProtocolException(String reason) {
	super(reason);
    }
}
