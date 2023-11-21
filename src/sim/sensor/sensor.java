package sim.sensor;


import sim.map;

public interface sensor { // return value is differ by sensor type
    String path = "/Users/spoof_uos/Library/Mobile Documents/com~apple~CloudDocs/Univ./2023년 2학기/소프트웨어공학/Software Engineering/addon-system/src/map.txt";
    public int[] getSensorValue(int[] pos, int[] direction);

}


