package add_on;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Server {
    public static void main(String[] args) {
        final int PORT = 9999; // 사용할 포트 번호

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("서버가 시작되었습니다. 클라이언트로부터 메시지를 기다립니다.");

            Socket clientSocket = serverSocket.accept(); // 클라이언트의 연결을 기다림

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String message;
            // DealingMessage 클래스의 인스턴스 생성
            DealingMessage dm = new DealingMessage();
            dm.startListening();
            while (!Objects.equals(message = reader.readLine(), "quit")) {
                System.out.println("클라이언트로부터 받은 메시지: " + message);

                // 받은 메시지를 DealingMessage 클래스의 addMessage() 메서드로 전달
                dm.addMessage(message);
            }

            reader.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}