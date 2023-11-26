import api.SocketManager;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        SocketManager.openServer();

        SocketManager.sendRequest("MRG/r0,1/\r");
        sleep(1000);
        SocketManager.sendRequest("MRG/r1,1/\r");
    }
}