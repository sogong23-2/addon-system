package sim.sensor;


import sim.map;

public interface sensor { // return value is differ by sensor type
    String path = "./map.txt";
    public map map = new map(4, 5);
    public int[] getSensorValue(int[] pos, int[] direction);
}


