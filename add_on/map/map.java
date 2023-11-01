class map{

    int r, c;

    int[][] map;

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
        return this.map[x][y];
    }

    public void add(int x, int y, int type){
        this.map[x][y] = type;
    }

}