package api;

import java.util.ArrayList;
import java.util.List;

public class SocketManager {
    public static List<String> receivedQueue = new ArrayList<>();

    public static void sendRequest(String data) {
        System.out.println("sendRequest: " + data);
        ResponseListener responseListener = response -> {
            System.out.println("response: " + response);
            receivedQueue.add(response);
        };
        SocketInstance socket = new SocketInstance(responseListener);
        socket.clientMode(data);
    }

    public static void openServer() {
        ResponseListener responseListener = response -> {
            System.out.println("response: " + response);
            receivedQueue.add(response);
        };
        SocketInstance socket = new SocketInstance(responseListener);
        socket.serverMode();
    }
}