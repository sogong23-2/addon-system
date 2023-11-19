package sim;

import java.util.Scanner;
import java.io.FileReader;

public class map {

    String path = "./map.txt";
    int r, c;

    int[][] map;

    public map(int r, int c){
        this.r = r;
        this.c = c;
        map = new int[r][c];
        this.initMap();
    }

    void initMap(){
        // get map from ./map.txt
        try {
            Scanner sc = new Scanner(new FileReader(path));
            for(int i = 0; i < r; i++){
                for(int j = 0; j < c; j++){
                    this.map[i][j] = sc.nextInt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMapValue(int x, int y){
        return this.map[x][y];
    }
}

