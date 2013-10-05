package skyport.api.example;

import java.io.IOException;
import java.net.UnknownHostException;

import skyport.api.SkyportConnection;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MockGraphicsClient {
    static Gson gson = new GsonBuilder()
    	.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
    	.create();
    
    public static class ConnectMessage {
	String message = "connect";
	int revision = 1;
	String password = "supersecretpassword";
	String laserstyle = "start-stop";
    }
    
    public static class ReadyMessage {
	String message = "ready";
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
	SkyportConnection conn = new SkyportConnection("localhost", 54331);
	conn.connect();
	
	String message = gson.toJson(new ConnectMessage());
	conn.send(message);
	
	String ready = gson.toJson(new ReadyMessage());
	while(message != null) {
	    System.out.println(message);
	    message = conn.read();
	    
	    conn.send(ready);
	}
	
    }
}
