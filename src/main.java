import add_on.*;

import javax.management.openmbean.SimpleType;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;

import static java.lang.System.exit;

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

    public static boolean visited_all(int[][] visited){
        for(int i = 0; i < visited.length; i++){
            for(int j = 0; j < visited[0].length; j++){
                if(visited[i][j] == 0) return false;
            }
        }
        return true;
    }

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

    static Queue move_to(map m, sim s, int[] target) {
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

                if(hazard[0] == 1) m.insertValue(position[0]+position[2], position[1]+position[3], HAZARD);
                if(colorblob[0] == 1)m.insertValue(position[0]+1, position[1]+0, COLOR_BLOB);
                if(colorblob[1] == 1)m.insertValue(position[0]+0, position[1]-1, COLOR_BLOB);
                if(colorblob[2] == 1)m.insertValue(position[0]-1, position[1]+0, COLOR_BLOB);
                if(colorblob[3] == 1)m.insertValue(position[0]+0, position[1]+1, COLOR_BLOB);

                if(hazard[0] == 1){
                    break;
                }

                s.forward();
                /*
                    잘못 움직일 경우 감지하여 복구하는 코드 작성 필요
                 */
                path.offer("forward ");
                position = s.getPosition();
                diff[0] = target[0] - position[0];
                diff[1] = target[1] - position[1];
            }

        }
        return path;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] position = new int[2];
        int[] direction = {1, 0};

        System.out.print("Map size: ");
        /*
        int r = sc.nextInt();
        int c = sc.nextInt();
        */
        int r = 5;
        int c = 6;
        System.out.println(r + " " + c);
        map m = new map(r, c);

        System.out.print("Starting point: ");
        /*
        position[0] = sc.nextInt();
        position[1] = sc.nextInt();
        */
        position[0] = 1;
        position[1] = 2;
        System.out.println(position[0] + " " + position[1]);

        sim s = new sim(position[0], position[1], r, c);



        m.insertValue(4, 2, SPOT);
        m.insertValue(1, 5, SPOT);
        /*
        m.insertValue(1, 0, HAZARD);
        m.insertValue(3, 2, HAZARD);
        m.insertValue(2, 5, HAZARD);
        m.insertValue(4, 4, COLOR_BLOB);
        */

        int[] spot1 = {4, 2};
        int[] spot2 = {1, 5};



        Queue<String> movement = move_to(m, s, spot1);

        while(!movement.isEmpty()){
            String next = movement.poll();
            System.out.print(next);
        }
        System.out.println();

        movement = move_to(m, s, spot2);

        while(!movement.isEmpty()){
            String next = movement.poll();
            System.out.print(next);
        }
        System.out.println("\n");




        int [][] map1 = m.getMap();
        for(int i = c-1; i >= 0; i--){
            for(int j = 0; j < r; j++){
                System.out.print(map1[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("fin");
    }
}