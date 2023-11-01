import com.unnamed.add_on.*;



public interface sensor {
    int getSensorValue(int x, int y);
}





public class hazard implements sensor {
    int getSensorValue(int x, int y) {
        return map.getMapValue(x, y);
    }



}

public class Colorblob implements sensor {

}


public class positioning implements sensor {
    int get SensorValue(int x, int y) {
        return sim.sim
    }

}



