package add_on;

import java.util.ArrayList;

public class SendingMessage {
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    String MGR(int x, int y){
        return "MGR/r"+x+","+y;
    }
    String UMG(ArrayList<Point> hazard, ArrayList<Point> blob){
        StringBuilder message = new StringBuilder("UMG/");
        for (Point point: hazard
             ) {
            message.append("h").append(point.x).append(",").append(point.y).append("/");
        }
        for (Point point: blob
        ) {
            message.append("b").append(point.x).append(",").append(point.y).append("/");
        }
        return message.toString();
    }
}
