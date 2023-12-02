package sim.sensor;

public class colorblob extends map {
    public int[] getSensorValue(int[] pos, int[] direction) {
        int[] result = new int[4];

        if (getMapValue(pos[0] + 1, pos[1]) == 8)
            result[0] = 1;

        if (getMapValue(pos[0], pos[1] - 1) == 8)
            result[1] = 1;

        if (getMapValue(pos[0] - 1, pos[1]) == 8)
            result[2] = 1;

        if (getMapValue(pos[0], pos[1] + 1) == 8)
            result[3] = 1;

        return result;
    }

    public colorblob(int r, int c){
        super(r,c);
    }
}
