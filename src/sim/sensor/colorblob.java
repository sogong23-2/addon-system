package sim.sensor;

public class colorblob implements sensor {  // return 0001, 0010, 0100, 1000 ( up, right, down, left )

    public int[] getSensorValue(int[] pos, int[] direction) {
        int[] result = new int[4];

        if (pos[0] + 1 < map.r){
            if (map.getMapValue(pos[0] + 1, pos[1]) == 8)
                result[0] = 1;
        }
        else
            result[0] = -1;

        if (pos[1] + 1 < map.c){
            if (map.getMapValue(pos[0], pos[1] + 1) == 8)
                result[1] = 1;
        }
        else
            result[1] = -1;

        if (pos[0] - 1 >= 0){
            if (map.getMapValue(pos[0] - 1, pos[1]) == 8)
                result[2] = 1;
        }
        else
            result[2] = -1;

        if (pos[1] - 1 >= 0){
            if (map.getMapValue(pos[0], pos[1] - 1) == 8)
                result[3] = 1;
        }
        else
            result[3] = -1;

        return result;
    }
}
