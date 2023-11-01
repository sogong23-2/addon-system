import com.unnamed.add_on.*;



public interface sensor { // return value is differ by sensor type
    int getSensorValue(int x, int y);
}

public class hazard implements sensor {
    int getSensorValue(int x, int y) {
        if (map.getMapValue(x, y) == 4)
            return 1;
        else
            return 0;
    }



}

public class Colorblob implements sensor {  // return 0001, 0010, 0100, 1000 ( up, right, down, left )
    int getSensorValue(int x, int y) {
        int result = 0;

        if (map.getMapValue(x+1, y); == 8)
            result |= 1;

        if (map.getMapValue(x, y+1); == 8)
            result |= 2;

        if (map.getMapValue(x-1, y); == 8)
            result |= 4;

        if (map.getMapValue(x, y-1); == 8)
            result |= 8;

        return result;
    }
}


public class positioning implements sensor {
    int get SensorValue(int x, int y) {
        return sim.sim
    }

}



