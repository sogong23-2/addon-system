package add_on;

public class map{

    private int r, c;

    private int[][] map;

    public map(int r, int c){
        this.r = r;
        this.c = c;
        map = new int[r][c];
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

}