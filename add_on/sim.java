package add_on;


public class sim {

    String path = "./map.txt";
    int[] direction = new int[2];
    int x, y;
    int[][] map;

    public void move() {
        this.x += direction[0];
        this.y += direction[1];
    }

    public void turn() {
        // {0, 1}, {1, 0}, {0, -1}, {-1, 0}
        if (direction[0] == 0 && direction[1] == 1) {
            direction[0] = 1;
            direction[1] = 0;
        } else if (direction[0] == 1 && direction[1] == 0) {
            direction[0] = 0;
            direction[1] = -1;
        } else if (direction[0] == 0 && direction[1] == -1) {
            direction[0] = -1;
            direction[1] = 0;
        } else if (direction[0] == -1 && direction[1] == 0) {
            direction[0] = 0;
            direction[1] = 1;
        }
    }

    public void scan() {
        int output;

        // scan hazard
        for (int i = 0; i < 4; i++) {
            output = sim.
                    hazard.getSensorValue(x + direction[0], y + direction[1]);
            if (output != 0) {
                map[x + direction[0]][y + direction[1]] = 4;
            }
            turn();
        }

        // scan color blob
        output = colorblob.getSensorValue(x, y);

        if ((output & 1) != 0) {
            map[x][y + 1] = 8;
        }
        if ((output & 2) != 0) {
            map[x + 1][y] = 8;
        }
        if ((output & 4) != 0) {
            map[x][y - 1] = 8;
        }
        if ((output & 8) != 0) {
            map[x - 1][y] = 8;
        }
    }

    public void setMap(int r, int c) {
        map = new int[r][c];
    }

    public void setStart(int x, int y) {
        this.x = x;
        this.y = y;
        map[x][y] = 1;
    }

    public void setSpot(int x, int y) {
        map[x][y] = 2;
    }

    public void setHazard(int x, int y) {
        map[x][y] = 4;
    }

    public sim() {
        direction[0] = 0;
        direction[1] = 1;
    }
}
