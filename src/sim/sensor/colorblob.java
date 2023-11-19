package sim.sensor;

public class colorblob implements sensor {  // return 0001, 0010, 0100, 1000 ( up, right, down, left )

    public int[] getSensorValue(int[] pos, int[] direction) {
        int[] result = new int[4];

        if (map.getMapValue(pos[0] + 1, pos[1]) == 8)
            result[0] = 1;

        if (map.getMapValue(pos[0], pos[1] + 1) == 8)
            result[1] = 1;

        if (map.getMapValue(pos[0] - 1, pos[1]) == 8)
            result[2] = 1;

        if (map.getMapValue(pos[0], pos[1] - 1) == 8)
            result[3] = 1;

        return result;
    }
}
