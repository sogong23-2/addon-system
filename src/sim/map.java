package sim;

import java.util.Scanner;
import java.io.FileReader;

public class map {
    String currentDirectory = System.getProperty("user.dir");
    String path = currentDirectory + "/src/map.txt";
    public int r, c;

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
            for(int i = 0; i < this.r; i++){
                for(int j = 0; j < this.c; j++){
                    this.map[i][j] = sc.nextInt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getMapValue(int x, int y){
        if (x < 0 || x >= r || y < 0 || y >= c) return -1;
        return this.map[x][y];
    }
}