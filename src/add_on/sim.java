/*package add_on;

import sim.main;

public class sim {
    main robot;

    public sim(int x, int y, int r, int c) {
        this.robot = new main(x,y,r,c);
    }

}

 */

package add_on;

import sim.*;
import sim.sensor.*;
import java.util.Random;

public class sim {
    sensor hazard, colorblob, positioning;
    movement move;
    map map;
    int[] position, direction; // 0: x, 1: y

    public sim (int x, int y, int r, int c) {
        hazard = new hazard();
        colorblob = new colorblob();
        positioning = new positioning();

        map = new map(r, c);

        this.position = new int[2];
        this.position[0] = x;
        this.position[1] = y;

        this.direction = new int[2];
        this.direction[0] = 0;
        this.direction[1] = 1;

        move = new movement();
    }

    public void forward(){
        this.position = move.forward(this.position, this.direction);
    }

    public void turn(){
        this.direction = move.turn(this.position, this.direction);
    }

    public int[][] scan(){

        int[] hazardValue = hazard.getSensorValue(this.position, this.direction);

        int[] colorblobValue = colorblob.getSensorValue(this.position, this.direction);

        return new int[][] {hazardValue, colorblobValue};
    }

    public int[] getPosition(){
        return positioning.getSensorValue(this.position, this.direction);
    }
}