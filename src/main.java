import add_on.*;

import java.util.Scanner;
import java.util.Stack;

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

    static void find_route(map m, sim s) {

    }

    static void scan_map(map m, sim s) {
        int[][] visited = new int[m.r][m.c];
        Stack<int[]> stack = new Stack<>();
        int[] pos = s.getPosition();
        stack.push(new int[]{pos[0], pos[1]});

        while(!stack.isEmpty()){
            int[] past = pos;
            int[] current = stack.pop();

            int x = current[0];
            int y = current[1];

            s.position[0] = x;
            s.position[1] = y;

            if(visited[x][y] == 1) continue;
            visited[x][y] = 1;

            int[][] sensorValue = s.scan();

            if(sensorValue[0][0] == 1) m.insertValue(x, y, HAZARD);
            if(sensorValue[1][0] == 1) m.insertValue(x+1, y, COLOR_BLOB);
            if(sensorValue[1][1] == 1) m.insertValue(x, y+1, COLOR_BLOB);
            if(sensorValue[1][2] == 1) m.insertValue(x-1, y, COLOR_BLOB);
            if(sensorValue[1][3] == 1) m.insertValue(x, y-1, COLOR_BLOB);

            //if(m.getMapValue(x, y) == HAZARD || m.getMapValue(x, y) == COLOR_BLOB) continue;

            if(x - 1 >= 0 && visited[x-1][y] == 0) stack.push(new int[]{x-1, y});
            if(x + 1 < m.r && visited[x+1][y] == 0) stack.push(new int[]{x+1, y});
            if(y - 1 >= 0 && visited[x][y-1] == 0) stack.push(new int[]{x, y-1});
            if(y + 1 < m.c && visited[x][y+1] == 0) stack.push(new int[]{x, y+1});

            find_route(m, s);
        }
        System.out.println("scan_map");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] position = new int[2];
        int[] direction = {0, 1};

        System.out.println("Map size: ");
        int r = sc.nextInt();
        int c = sc.nextInt();
        map m = new map(r, c);

        System.out.println("Starting point: ");
        position[0] = sc.nextInt();
        position[1] = sc.nextInt();
        sim s = new sim(position[0], position[1], 4, 5);

        System.out.println("Spot: ");


        //m.insertValue(1, 0, HAZARD);
        //m.insertValue(3, 2, HAZARD);
        //m.insertValue(3, 2, COLOR_BLOB);
        //m.insertValue(1, 3, COLOR_BLOB);

        System.out.println("done");// exit(0);

        scan_map(m, s);

        int [][] map = m.getMap();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}