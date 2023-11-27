import add_on.*;
import api.SocketManager;
import api.TokenEncoder;
import api.*;
import java.util.*;

import static java.lang.Thread.sleep;


/*
          Map size: 4 x 5
    Starting point: (1, 2)
              Spot: (4, 2), (1, 5)
            Hazard: (1, 0), (3, 2)
 */

/*
    지도의 숫자의 의미
    0: 탐색이 안된 지역
    1: 안전한 지역 ( 아무것도 없는 지역 )
    2: predefined spot
    4: hazard
    8: color blob
 */


public class main {
    public static final int UNEXPLORED = 0;
    public static final int SAFE = 1;
    public static final int SPOT = 2;
    public static final int HAZARD = 4;
    public static final int COLOR_BLOB = 8;

    int received_command = 0;

    static Stack<int[]> find_route(map m, sim s, int[] position, int[] target) {
        //print arguments
        int[] initial = position;
        int[][] map = m.getMap();
        int[][] path = new int[map.length][map[0].length];
        int[][] visited = new int[map.length][map[0].length];
        for(int i=0; i<map.length; i++){
            for(int j=0; j<map[0].length; j++){
                if (map[i][j] == HAZARD) visited[i][j] = 1;
            }
        }

        int[][] next = {{1,0},{0,-1},{-1,0},{0,1}};

        Queue<int[]> q = new LinkedList<>();

        q.offer(position);
        visited[position[0]][position[1]] = 1;

        while(!q.isEmpty()){
            int[] current = q.poll();
            if(current[0] == target[0] && current[1] == target[1]) {
                break;
            }

            for(int i = 0; i < 4; i++){
                int[] next_position = {current[0]+next[i][0], current[1]+next[i][1]};
                if(next_position[0] < 0 || next_position[0] >= map.length || next_position[1] < 0 || next_position[1] >= map[0].length) continue;
                if(visited[next_position[0]][next_position[1]] == 1) continue;

                q.add(next_position);
                visited[next_position[0]][next_position[1]] = 1;
                path[next_position[0]][next_position[1]] = i;
            }
        }

        Stack<int[]> stack = new Stack<>();
        int[] current = target;
        while(current[0] != initial[0] || current[1] != initial[1]){
            stack.push(current);
            int[] next_position = {current[0]-next[path[current[0]][current[1]]][0], current[1]-next[path[current[0]][current[1]]][1]};
            current = next_position;
        }

        return stack;

    }

    static Queue move_to(map m, sim s, int[] target) throws InterruptedException{
        int position[] = s.getPosition();
        int[] diff = {target[0] - position[0], target[1] - position[1]};

        Stack<int[]> movement;
        Queue<String> path = new LinkedList<>();

        while (!(diff[0] == 0 && diff[1] == 0)) {
            movement =  find_route(m, s, position, target);

            while (!movement.isEmpty()) {
                int[] next = movement.pop();
                position = s.getPosition();
                int[] move = {next[0] - position[0], next[1] - position[1]};

                //System.out.println(position[2] + " " + position[3]);
                while(move[0] != position[2] || move[1] != position[3]) {
                    s.turn();
                    path.offer("turn ");
                    position = s.getPosition();
                }


                int[][] scan_result = s.scan();
                int[] hazard = scan_result[0];
                int[] colorblob = scan_result[1];

                if(hazard[0] == 1) {SocketManager.sendRequest(TokenEncoder.tokenSensored(position[0]+position[2], position[1]+position[3], HAZARD)); m.insertValue(position[0]+position[2], position[1]+position[3], HAZARD);}
                if(colorblob[0] == 1) {SocketManager.sendRequest(TokenEncoder.tokenSensored(position[0]+1, position[1]+0, COLOR_BLOB)); m.insertValue(position[0]+1, position[1]+0, COLOR_BLOB);}
                if(colorblob[1] == 1) {SocketManager.sendRequest(TokenEncoder.tokenSensored(position[0]+0, position[1]-1, COLOR_BLOB)); m.insertValue(position[0]+0, position[1]-1, COLOR_BLOB);}
                if(colorblob[2] == 1) {SocketManager.sendRequest(TokenEncoder.tokenSensored(position[0]-1, position[1]+0, COLOR_BLOB)); m.insertValue(position[0]-1, position[1]+0, COLOR_BLOB);}
                if(colorblob[3] == 1) {SocketManager.sendRequest(TokenEncoder.tokenSensored(position[0]+0, position[1]+1, COLOR_BLOB)); m.insertValue(position[0]+0, position[1]+1, COLOR_BLOB);}

                if(hazard[0] == 1){
                    break;
                }

                s.forward();
                /*
                    잘못 움직일 경우 감지하여 복구하는 코드 작성 필요
                 */
                path.offer("forward ");
                System.out.println("forward\n");
                position = s.getPosition();
                SocketManager.sendRequest(TokenEncoder.tokenMoveRobot(position[0], position[1]));
                diff[0] = target[0] - position[0];
                diff[1] = target[1] - position[1];
                sleep(1000);

                /*
                check received socket
                 */

                if (SocketManager.receivedQueue.size() > 0) {
                    String data = SocketManager.receivedQueue.get(0);
                    SocketManager.receivedQueue.remove(0);
                    int[][] ret = SocketHandler.apiResolver(data);
                    if (ret[0][0] == -1) {
                        while (true) {
                            if (SocketManager.receivedQueue.size() > 0) {
                                data = SocketManager.receivedQueue.get(0);
                                SocketManager.receivedQueue.remove(0);
                                ret = SocketHandler.apiResolver(data);
                                if (ret[0][0] == -2) {
                                    break;
                                }
                            }
                        }
                    }
                    else{
                        m.insertValue(ret[0][0], ret[0][1], ret[0][2]);
                    }
                }
            }
        }
        return path;
    }

    public static void main(String[] args) throws InterruptedException {
        SocketManager.openServer();

        int[][] map_init;
        while (true) {
            if (SocketManager.receivedQueue.size() > 0) {
                String data = SocketManager.receivedQueue.get(0);
                SocketManager.receivedQueue.remove(0);
                System.out.println(data);
                map_init = SocketHandler.apiResolver(data);
                break;
            }
        }

        int init_length = map_init[0][0];

        map m = null;
        int r = 0;
        int c = 0;
        int[] position = new int[2];

        int[][] targets = {{-1, -1}, {-1, -1}};

        for(int i=0; i<init_length; i++){
            if (map_init[i][3] == 'r' - '0')
            {
                position[0] = map_init[i][0];
                position[1] = map_init[i][1];
            }
            if (map_init[i][3] == 'm' - '0')
            {
                r = map_init[i][0];
                c = map_init[i][1];
                m = new map(r, c);
            }
            if (map_init[i][3] == 'b' - '0')
            {
                m.insertValue(map_init[i][0], map_init[i][1], COLOR_BLOB);
            }
            if (map_init[i][3] == 'h' - '0')
            {
                m.insertValue(map_init[i][0], map_init[i][1], HAZARD);
            }
            if (map_init[i][3] == 't' - '0') {
                if (targets[0][0] == -1){
                    targets[0][0] = map_init[i][0];
                    targets[0][1] = map_init[i][1];
                }
                else {
                    targets[1][0] = map_init[i][0];
                    targets[1][1] = map_init[i][1];
                }
            }
        }

        int[] direction = {1, 0};

        System.out.println("Map size: " + r + " " + c);

        System.out.print("Starting point: ");
        System.out.println(position[0] + " " + position[1]);

        sim s = new sim(position[0], position[1], r, c);

        Queue<String> movement = move_to(m, s, targets[0]);

        while (!movement.isEmpty()) {
            String next = movement.poll();
            System.out.print(next);
        }
        System.out.println();

        movement = move_to(m, s, targets[1]);

        while (!movement.isEmpty()) {
            String next = movement.poll();
            System.out.print(next);
        }
        System.out.println("\n");


        int[][] map1 = m.getMap();
        for (int i = c - 1; i >= 0; i--) {
            for (int j = 0; j < r; j++) {
                System.out.print(map1[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("fin");
    }
}

/*
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
*/

