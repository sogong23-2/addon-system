import com.unnamed.add_on.*;
import com.unnamed.sim.*;

//import com.umnamed.sim.*;

/*
          Map size: 4 x 5
    Starting point: (1, 2)
              Spot: (4, 2), (1, 5)
            Hazard: (1, 0), (3, 2)
 */

/*
    지도의 숫자의 의미

    0: 탐색이 안된 지역

    1: 안전한 지역 ( 아무것도 없는 지역 )

    2: predefined spot

    4: hazard

    8: color blob
 */


public class main {
    public static void main(String[] args) {
        sim s = new sim();

        s.initMap(4, 5);
        s.setStart(1, 2);
        s.setSpot(4, 2);
        s.setSpot(1, 5);
        s.setHazard(1, 0);
        s.setHazard(3, 2);

    }
}