public class sim{

    int direction[2] = {0, 1);

    int x, y;

    int map[][];

    void move(void){
        this.x += direction[0];
        this.y += direction[1];
    }

    void turn(void){
        // {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        switch (direction) {
            case {0, 1}:
                direction = {1, 0};
                break;
            case {1, 0}:
                direction = {0, -1};
                break;
            case {0, -1}:
                direction = {-1, 0};
                break;
            case {-1, 0}:
                direction = {0, 1};
                break;
        }

    }

    void scan(void){

        int output

        // scan hazard
        for(int i=0; i<4; i++){
            output = sensor.hazard.getSensorValue(x+direction[0], y+direction[1]);
            if output:
                map[x+direction[0]][y+direction[1]] = 4;
            turn();
        }

        // scan color blob

        output = sensor.colorblob.getSensorValue(x, y);

        if output & 1:
            map[x][y+1] = 8;
        if output & 2:
            map[x+1][y] = 8;
        if output & 4:
            map[x][y-1] = 8;
        if output & 8:
            map[x-1][y] = 8;

    }


    void setMap(int r, int c){
        map = new int[r][c];
    }

    void setStart(int x, int y){
        this.x = x;
        this.y = y;
        map[x][y] = 1;
    }

    void setSpot(int x, int y){
        map[x][y] = 2;
    }

    void setHazard(int x, int y){
        map[x][y] = 4;
    }


}
