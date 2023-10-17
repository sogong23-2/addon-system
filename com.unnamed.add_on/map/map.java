class map{

    int r, c;

    int[][] map;

    public map(int r, int c){
        this.r = r;
        this.c = c;
        map = new int[r][c];
        this.initMap();
    }

    public initMap(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                this.map[i][j] = 0;
            }
        }
    }

    public void add(int x, int y, int type){
        this.map[x][y] = type;
    }

    public static void main(String[] args) {

    }

}