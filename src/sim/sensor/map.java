package sim.sensor;

import java.io.FileReader;
import java.util.Scanner;

public class map implements sensor {
    private String currentDirectory = System.getProperty("user.dir");
    private String path = currentDirectory + "/src/map.txt";
    private int r, c;

    private int[][] map;

    public map(int r, int c){
        this.r = r;
        this.c = c;
        map = new int[r][c];
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

    protected int getMapValue(int x, int y){
        if (x < 0 || x >= r || y < 0 || y >= c) return -1;
        return this.map[x][y];
    }

    @Override
    public int[] getSensorValue(int[] pos, int[] direction) {
        return new int[0];
    }
}

