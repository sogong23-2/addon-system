package api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

class SocketInstance {
    private final ResponseListener responseListener;

    //TODO change init settings
    private final int port = 5002;
    private final String destinationIP = "10.0.2.16";
    private final int destinationPort = 5001;

    public SocketInstance(ResponseListener responseListener) {
        this.responseListener = responseListener;
    }

    public void serverMode() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Server started, waiting for connections...");

                while (true) {
                    //Socket waiting....
                    Socket clientSocket = serverSocket.accept();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
                    System.out.println(clientSocket.getInetAddress().getHostAddress() + " connected");
                    String request = reader.readLine();
                    System.out.println("request: " + request);

                    SocketHandler.apiResolver(request);

                    writer.write("ACK/r");

                    writer.close();
                    reader.close();
                    clientSocket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void clientMode(String request) {
        new Thread(() -> {
            try {
                Socket clientSocket = new Socket(destinationIP, destinationPort);

                OutputStreamWriter writer = new OutputStreamWriter(clientSocket.getOutputStream());
                writer.write(request);
                writer.flush();

                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String response = reader.readLine();
                responseListener.onResponseReceived(response);

                writer.close();
                reader.close();
                clientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
