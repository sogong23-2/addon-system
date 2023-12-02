package sim.sensor;


import sim.map;

public interface sensor { // return value is differ by sensor type
    public int[] getSensorValue(int[] pos, int[] direction);

}