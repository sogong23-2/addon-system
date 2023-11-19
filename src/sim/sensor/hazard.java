package sim.sensor;

public class hazard implements sensor {
    public int[] getSensorValue(int[] pos, int[] direction) {
        if (map.getMapValue(pos[0]+direction[0], pos[1]+direction[1]) == 4)
            return new int[] {1};
        else
            return new int[] {0};
    }

}
