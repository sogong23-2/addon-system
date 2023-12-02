package add_on;

import java.util.ArrayList;
public class GeneratingPath {
    public static final int SAFE = 0;
    public static final int HAZARD = 4;
    public static final int CRITICAL = 8;
    public static final int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
    private int[][] map;
    // 생성자
    GeneratingPath(int[][] map){
        this.map = map;
    }
    public static class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    // 지도상에 중요지점이 존재하는지 확인
    private boolean isExistCritical(){
        int m = map[0].length;

        for (int[] ints : map) {
            for (int j = 0; j < m; j++) {
                if (ints[j] == CRITICAL)
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, boolean[][] visited, ArrayList<Point> path) {
        int n = map.length;
        int m = map[0].length;

        if (map[x][y] == CRITICAL) {
            path.add(new Point(x, y));
            map[x][y] = SAFE; // 중요 지점을 찾으면 안전지역으로 변환
            return true;
        }

        visited[x][y] = true;

        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];

            // x, y가 범위를 벗어나지 않고, newX newY가 HAZARD가 아니면서 방문하지 않은 곳으로 이동
            if (newX >= 0 && newX < n && newY >= 0 && newY < m
                    && map[newX][newY] != HAZARD && !visited[newX][newY]) {
                if (dfs(newX, newY, visited, path)) {
                    path.add(0, new Point(x, y)); // 경로 복원을 위해 역순으로 저장
                    return true;
                }
            }
        }

        return false;
    }

    public ArrayList<Point> findPath(int startX, int startY) {
        int n = map.length;
        int m = map[0].length;
        int currentX = startX;
        int currentY = startY;
        ArrayList<Point> realPath = new ArrayList<>();

        // 목적지가 여러개이므로 dfs를 여러번 적용하여 경로찾기
        while(isExistCritical()) {
            // tmpPath, realPath를 따로 둔 이유는 재귀함수인 dfs를 지나면 path를 거꾸로 넣기 때문에
            ArrayList<Point> tmpPath = new ArrayList<>();
            boolean[][] visited = new boolean[n][m];
            boolean found = dfs(currentX, currentY, visited, tmpPath);
            currentX = tmpPath.get(tmpPath.size() - 1).x;
            currentY = tmpPath.get(tmpPath.size() - 1).y;
            realPath.addAll(tmpPath);

            if (!found) {
                System.out.println("목적지에 도달할 수 없습니다.");
            }
        }
        return realPath;
    }
    /*
    // 테스트 코드
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("지도의 크기 n m 입력: ");
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[][] map = new int[n][m];

        System.out.println("지도의 정보 입력:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = scanner.nextInt();
            }
        }

        System.out.print("출발 위치 x y 입력: ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        List<Point> path = findPath(map, startX, startY);

        if (!path.isEmpty()) {
            System.out.println("경로:");
            for (Point point : path) {
                System.out.println("(" + point.x + ", " + point.y + ")");
            }
        }
    }

     */
}
// 문제점: critical의 path의 Point 중복됨