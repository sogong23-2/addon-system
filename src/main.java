import add_on.*;


import java.util.Scanner;
import java.util.Stack;


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

    static void find_route(map m, sim s){
        Stack<int[]> stack = new Stack<>();

        



    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] position = new int[2];
        int[] direction = {0, 1};

        System.out.println("Map size: ");
        int r = sc.nextInt();
        int c = sc.nextInt();
        System.out.println("Starting point: ");
        position[0] = sc.nextInt();
        position[1] = sc.nextInt();
        System.out.println("Spot: ");

        map m = new map(r, c);
        sim s = new sim(position[0], position[1], 4, 5);

        m.insertValue(1, 0, HAZARD);
        m.insertValue(3, 2, HAZARD);
        m.insertValue(4, 2, COLOR_BLOB);
        m.insertValue(1, 5, COLOR_BLOB);


        int[][] visited = new int[r][c];
        boolean visited_all = false;

        while(!visited_all)
        {
            s.forward();

            int pos[] = s.getPosition();
            if( pos != position){
                s.turn();
                s.turn();
                s.forward();
                s.turn();
                s.turn();
            }

            int[][] scan_result =  s.scan();

            int[] hazard = scan_result[0];
            int[] colorblob = scan_result[1];

            if(hazard[0] == 1){
                m.insertValue(pos[0] + direction[0], pos[1] + direction[1], HAZARD);
            }

            if(colorblob[0] == 1){
                m.insertValue(pos[0] + direction[0], pos[1] + direction[1], COLOR_BLOB);
            }

            find_route(m, s);

            visited[pos[0]][pos[1]] = 1;

        }

    }
}