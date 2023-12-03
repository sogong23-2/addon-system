package add_on;

import sim.movement;
import sim.sensor.*;
import java.util.Random;

public class sim {
    private sensor hazard, colorblob, positioning;
    private movement move;
    private int [] position, direction; // 0: x, 1: y
    private int r, c;
    private boolean flag = false;

    public sim (int x, int y, int r, int c) {
        this.r = r;
        this.c = c;
        hazard = new hazard(r, c);
        colorblob = new colorblob(r, c);
        positioning = new positioning(r, c);


        this.position = new int[2];
        this.position[0] = x;
        this.position[1] = y;

        this.direction = new int[2];
        this.direction[0] = 1;
        this.direction[1] = 0;

        move = new movement();
    }

    public void forward(){
        if (!this.flag) {
            this.position = move.forward(this.position, this.direction);

            Random rand = new Random();
            int randomNum = rand.nextInt((100 - 1) + 1) + 1;
            if (randomNum <= 100) {
                int t = hazard.getSensorValue(this.position, this.direction)[0];
                int[] tmp = move.forward(this.position, this.direction);
                if (tmp[0] >= 0 && tmp[0] < this.r && tmp[1] >= 0 && tmp[1] < this.c && t != 1) {
                    this.position = move.forward(this.position, this.direction);
                    this.flag = true;
                }
            }
        }
        else {
            this.flag = false;
            this.position = move.forward(this.position, this.direction);
        }
    }

    public void turn(){
        this.direction = move.turn(this.position, this.direction);
    }

    public int[][] scan(){

        int turn = 0;
        while (this.direction == new int[] {1,0}) {
            move.turn(this.position, this.direction);
            turn++;
        }

        int [] hazardValue = new int[4];

        for(int i=0; i<4; i++){
            hazardValue[i] = hazard.getSensorValue(this.position, this.direction)[0];
            this.direction = move.turn(this.position, this.direction);
        }

        int[] colorblobValue = colorblob.getSensorValue(this.position, this.direction);

        for(int i=0; i<turn; i++){
            move.turn(this.position, this.direction);
        }

        return new int[][] {hazardValue, colorblobValue};
    }

    public int[] getPosition(){
        return positioning.getSensorValue(this.position, this.direction);
    }
}