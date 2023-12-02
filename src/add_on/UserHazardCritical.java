package add_on;

import java.util.ArrayList;

// String형 메시지를 잘라 hazard critical를 hazard_critical_map에 적용
public class UserHazardCritical {
    static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static final int SAFE = 0;
    public static final int HAZARD = 4;
    public static final int CRITICAL = 8;
    private ArrayList<Point> hazard;
    private ArrayList<Point> critical;
    int[][] map;
    //생성자
    UserHazardCritical(int[][] map){
        this.hazard = new ArrayList<Point>();
        this.critical = new ArrayList<Point>();
        this.map = map;
    }
    // 문장을 자르고 각 점의 좌표를 hazard와 critical 리스트에 넣기
    public void stringToInteger(ArrayList<String> text){
        // 공백을 기준으로 자르고
        String[] splitedtext = text.get(0).split(" ");
        int cursor = 0;
        while (cursor < splitedtext.length) {
            String current = splitedtext[cursor++];
            if (current.contains("위험")) {
                hazard.add(new Point(Integer.parseInt(splitedtext[cursor++]), Integer.parseInt(splitedtext[cursor++])));
            } else if (current.contains("중요")) {
                critical.add(new Point(Integer.parseInt(splitedtext[cursor++]), Integer.parseInt(splitedtext[cursor++])));
            }
        }
    }

    // hazard와 critical 리스트를 Map에 적용
    public int[][] Apply(){
        for (Point point : hazard) {
            int x = point.x;
            int y = point.y;
            map[x][y] = HAZARD;
        }
        for (Point point : critical) {
            int x = point.x;
            int y = point.y;
            map[x][y] = CRITICAL;
        }
        return map;
    }
}