package skyport.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SkyportConnection {
    private String host;
    private int port; 
    private Socket socket;
    
    private BufferedWriter out;
    private BufferedReader in;
    	
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
	    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	} catch (IOException e) {
	    System.out.println("Failed to get output stream.");
	    connected = false;
	}
	
	try {
	    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	} catch (IOException e) {
	    System.out.println("Failed to get input stream");
	    connected = false;
	}
	
	return connected;
    }
    
    public boolean send(String json) {
	boolean sent = true;
	try {
	    out.write(json);
	    out.newLine();
	    out.flush();
	} catch (IOException e) {
	    sent = false;
	    System.out.println("Could not send packet.");
	}
	return sent;
    }
    
    public String read() {
	String json = null;
	try {
	    json = in.readLine();
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
