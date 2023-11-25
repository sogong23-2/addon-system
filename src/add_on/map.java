package add_on;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class map{

    public int r, c;

    int[][] map;
    int robotX;
    int robotY;

    public map(int r, int c){
        this.r = r;
        this.c = c;
        map = new int[r][c];
        this.initMap();
    }

    void initMap(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                this.map[i][j] = 0;
            }
        }
    }

    public int getMapValue(int x, int y){
        if (x < 0 || x >= r || y < 0 || y >= c) return -1;
        return this.map[x][y];
    }

    public void insertValue(int x, int y, int type){
        this.map[x][y] = type;
        //send_data(this.map);
    }

    public int[][] getMap(){
        return this.map;
    }

    void send_data(int[][] map){
        // send data to server
        // use socket

        String data = "";
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                data += map[i][j];
            }
        }

        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            Scanner serverInput = new Scanner(socket.getInputStream());
            PrintWriter clientOutput = new PrintWriter(socket.getOutputStream(), true);
            Scanner userInput = new Scanner(System.in)) {

            // 서버에 연결되면 사용자로부터 메시지 입력
            while (true) {
                System.out.print("메시지를 입력하세요: ");
                String message = data;

                // 서버로 메시지 전송
                clientOutput.println(message);

                // 서버에서의 응답 수신 및 출력
                if (serverInput.hasNextLine()) {
                    String serverMessage = serverInput.nextLine();
                    System.out.println("서버: " + serverMessage);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}