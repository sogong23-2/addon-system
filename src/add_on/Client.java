package add_on;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) {
        final String SERVER_IP = "127.0.0.1"; // 서버의 IP 주소
        final int SERVER_PORT = 9999; // 서버의 포트 번호

        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

            // 메시지를 보냅니다.
            Scanner sc = new Scanner(System.in);
            while(true) {
                String msg = sc.next();
                if (Objects.equals(msg, "quit"))
                    break;
                writer.println(msg);
                writer.flush();
            }

            writer.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}