package skyport.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class SkyportConnection {
    private String host;
    private int port;
    private Socket socket;

    private BufferedWriter out;
    private BufferedReader in;

    /**
     * Sets up the connection to the server.
     * 
     * @param host
     *            The hostname or ip-address of the server.
     * @param port
     *            The port name the server is listening to.
     */
    public SkyportConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * Connects to the server.
     * 
     * Sets up the writer and reader for the socket.
     * 
     * @return true if the connection was successful.
     */
    public boolean connect() {
        boolean connected = true;
        try {
            InetAddress a = InetAddress.getByName(host);
            socket = new Socket(a, port);
        } catch (UnknownHostException e) {
            System.err.println("The host '" + host + "' does not exist.");
            connected = false;
        } catch (IOException e) {
            System.err.println("Problem with connection.");
            connected = false;
        }

        try {
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            System.err.println("Failed to get output stream.");
            connected = false;
        }

        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Failed to get input stream");
            connected = false;
        }

        return connected;
    }

    /**
     * Sends the json encoded string to the server.
     * 
     * @param json
     *            json encoded as a string.
     * @return returns true if the send was successful.
     */
    public boolean send(String json) {
        boolean sent = true;
        try {
            out.write(json);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            sent = false;
            System.err.println("Could not send packet.");
        }
        return sent;
    }

    /**
     * Blocks on read from the server.
     * 
     * @return Returns the next json encoded string.
     */
    public String read() {
        String json = null;
        try {
            json = in.readLine();
        } catch (IOException e) {
            System.err.println("Could not recieve packet properly.");
        }
        return json;
    }

    /**
     * Closes the connection to the server.
     */
    public void close() {
        try {
            this.socket.close();
        } catch (IOException e) {
            System.err.println("Could not close connection.");
        }
    }
}
