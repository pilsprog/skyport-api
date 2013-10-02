package skyport.api;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SkyportConnection {
    private String host;
    private int port; 
    private Socket socket;
    
    private DataOutputStream out;
    private DataInputStream in;
    
    public SkyportConnection(String host, int port) {
	this.host = host;
	this.port = port;
    }
    
    public boolean connect() {
	boolean connected = true;
	try {
	    socket = new Socket(host, port);
	} catch (UnknownHostException e) {
	    System.out.println("The host '" + host + ":" + port + "' does not exist.");
	    connected = false;
	} catch (IOException e) {
	    System.out.println("Problem with connection.");
	    connected = false;
	}
	
	try {
	    out = new DataOutputStream(socket.getOutputStream());
	} catch (IOException e) {
	    System.out.println("Failed to get output stream.");
	    connected = false;
	}
	
	try {
	    in = new DataInputStream(socket.getInputStream());
	} catch (IOException e) {
	    System.out.println("Failed to get input stream");
	    connected = false;
	}
	
	return connected;
    }
    
    public boolean sendPacket(String json) {
	boolean sendt = true;
	try {
	    out.writeUTF(json);
	} catch (IOException e) {
	    sendt = false;
	    System.out.println("Could not send packet.");
	}
	return sendt;
    }
    
    public String recievePacket() {
	String json = "";
	try {
	    json = in.readUTF();
	} catch (IOException e) {
	    System.out.println("Could not recieve packet properly.");
	}
	return json;
    }
    
    public void close() {
	try {
	    this.socket.close();
	} catch (IOException e) {
	    System.out.println("Could not close connection.");
	}
    }
}
