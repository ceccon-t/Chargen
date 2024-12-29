package dev.ceccon.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AIServerConnection {

    HttpURLConnection connection;

    private AIServerConnection(String urlString) throws IOException {
        URL url = new URL(urlString);

        HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
        httpConnection.setRequestMethod("POST");
        httpConnection.setDoOutput(true);
        httpConnection.setRequestProperty("Content-Type", "application/json");
        httpConnection.setRequestProperty("Accept", "application/json");

        this.connection = httpConnection;
    }

    public static AIServerConnection forUrl(String urlString) throws IOException {
        return new AIServerConnection(urlString);
    }

    public void send(String body) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
            dos.writeBytes(body);
            dos.flush();
        }
    }

    public BufferedReader getBufferedReader() throws IOException {
        return new BufferedReader(new InputStreamReader(connection.getInputStream()));
    }

    public void close() {
        this.connection.disconnect();
    }
}
