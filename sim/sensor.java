package sim;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


interface sensor { // return value is differ by sensor type
    String path = "./map.txt";
    int getSensorValue(int x, int y);
}

public class hazard implements sensor {
    public int getSensorValue(int x, int y) {
        if (getMapValue(x, y) == 4)
            return 1;
        else
            return 0;
    }



}

public class Colorblob implements sensor {  // return 0001, 0010, 0100, 1000 ( up, right, down, left )
    public int getSensorValue(int x, int y) {
        int result = 0;

        if (map.getMapValue(x+1, y) == 8)
            result |= 1;

        if (map.getMapValue(x, y+1) == 8)
            result |= 2;

        if (map.getMapValue(x-1, y) == 8)
            result |= 4;

        if (map.getMapValue(x, y-1) == 8)
            result |= 8;

        return result;
    }
}


public class positioning implements sensor {
    public int getSensorValue(int x, int y) {
        return 0;
    }

}
