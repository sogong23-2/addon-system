import api.SocketManager;
import api.TokenEncoder;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world!");
        SocketManager.openServer();
        sleep(2000);
        SocketManager.sendRequest(TokenEncoder.tokenMoveRobot(0, 1));
        sleep(2000);
        SocketManager.sendRequest(TokenEncoder.tokenMoveRobot(1, 1));
        sleep(2000);
        SocketManager.sendRequest(TokenEncoder.tokenSensored("b1,0/"));
        sleep(2000);
        SocketManager.sendRequest(TokenEncoder.tokenMoveRobot(2, 1));
        sleep(2000);
        SocketManager.sendRequest(TokenEncoder.tokenMoveRobot(3, 1));

    }
}