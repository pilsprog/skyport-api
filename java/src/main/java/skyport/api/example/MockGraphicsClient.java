package skyport.api.example;

import skyport.api.SkyportConnection;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MockGraphicsClient implements Runnable {
    static Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES).create();

    public static class ConnectMessage {
        String message = "connect";
        int revision = 1;
        String password = "supersecretpassword";
        String laserstyle = "start-stop";
    }

    public static class ReadyMessage {
        String message = "ready";
    }

    private String host;
    private int port;

    public MockGraphicsClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {
        SkyportConnection conn = new SkyportConnection(host, port);
        conn.connect();

        String message = gson.toJson(new ConnectMessage());
        conn.send(message);

        String ready = gson.toJson(new ReadyMessage());
        while (message != null) {
            System.out.println(message);
            message = conn.read();

            conn.send(ready);
        }
    }
}
