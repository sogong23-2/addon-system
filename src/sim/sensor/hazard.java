package sim.sensor;

public class hazard implements sensor {
    public int[] getSensorValue(int[] pos, int[] direction) {
        if (pos[0]+direction[0] < 0 || pos[0]+direction[0] >= map.r || pos[1]+direction[1] < 0 || pos[1]+direction[1] >= map.c)
            return new int[] {-1};
        if (map.getMapValue(pos[0]+direction[0], pos[1]+direction[1]) == 4)
            return new int[] {1};
        else
            return new int[] {0};
    }

}
