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
    private String destinationIP = "172.30.122.18";
    private int destinationPort = 5001;

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
                    String request = reader.readLine();

                    SocketHandler.apiResolver(request);

//
//                    AddressInfo addressInfo =
//                            new AddressInfo(
//                                    clientSocket.getInetAddress().getHostAddress(),
//                                    clientSocket.getPort()
//                            );
//
//                    destinationIP = addressInfo.getIpAddress();
//                    destinationPort = addressInfo.getPort();
//                    System.out.println("destinationIP: " + destinationIP);
//                    System.out.println("destinationPort: " + destinationPort);

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
