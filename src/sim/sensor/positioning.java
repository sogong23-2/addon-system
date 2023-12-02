package sim.sensor;

public class positioning extends map {

    public int[] getSensorValue(int[] pos, int[] direction) {
        // 0x FF FF FF FF
        //    x  y  {direction}
        // direction: {(0,1),(1,0),(0,-1),(-1,0)}

        int[] sensorVal = new int[4];

        sensorVal[0] = pos[0];
        sensorVal[1] = pos[1];
        sensorVal[2] = direction[0];
        sensorVal[3] = direction[1];

        return sensorVal;
    }
    public positioning(int r, int c){
        super(r,c);
    }

}
