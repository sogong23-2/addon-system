package add_on;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;

public class Server {
    public static void main(String[] args) {
        final int PORT = 9999; // 사용할 포트 번호

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("서버가 시작되었습니다. 클라이언트로부터 메시지를 기다립니다.");

            Socket clientSocket = serverSocket.accept(); // 클라이언트의 연결을 기다림

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String message;
            // DealingMessage 클래스의 인스턴스 생성
            DealingMessage dm = new DealingMessage();
            dm.startListening();

            SendingMessage sm = new SendingMessage();
            ArrayList<GeneratingPath.Point> tmpmovement = new ArrayList<>();

            while (!Objects.equals(message = reader.readLine(), "quit")) {
                System.out.println("클라이언트로부터 받은 메시지: " + message);

                // 받은 메시지를 DealingMessage 클래스의 addMessage() 메서드로 전달
                dm.addMessage(message);

                if(!Objects.isNull(DealingMessage.map.map)) {
                    // 센서에 hazard나 blob이 감지됐으면 경로 다시 계산, 초깃값은 true여야 함
                    if(isChanged()) {
                        // tmpmap에 가져와서 경로 계산
                        int[][] tmpmap = new int[DealingMessage.map.map.length][DealingMessage.map.map[0].length];
                        for (int i = 0; i < DealingMessage.map.map.length; i++)
                            System.arraycopy(DealingMessage.map.map[i], 0, tmpmap[i], 0, DealingMessage.map.map[0].length);
                        GeneratingPath gp = new GeneratingPath(tmpmap);
                        tmpmovement = gp.findPath(DealingMessage.map.robotX, DealingMessage.map.robotY);
                    }
                    // 로봇 위치 업데이트
                    tmpmovement.remove(0);
                    int nextX = tmpmovement.get(0).x;
                    int nextY = tmpmovement.get(0).y;
                    DealingMessage.map.robotX = nextX;
                    DealingMessage.map.robotY = nextY;
                    // 메시지 보내기
                    writer.println(sm.MGR(nextX, nextY));
                    writer.flush();
                }
            }

            reader.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}