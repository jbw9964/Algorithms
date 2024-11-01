import java.util.*;
import java.util.stream.*;

public class Solution {

    public static void main(String[] args) {
        int[][] points = {{2, 2}, {2, 3}, {2, 7}, {6, 6}, {5, 2}};
        int[][] routes = {{2, 3, 4, 5}, {1, 3, 4, 5}};

        System.out.println(new Solution().solution(points, routes));
    }

    public int solution(int[][] points, int[][] routes) {

        RobotManager manager = new RobotManager(points, routes);

        int answer = 0;

        while (!manager.isFinished())   {
            manager.moveRobots();
            answer += manager.getCollisions();
        }

        return answer;
    }
}

class Robot {

    int r, c;
    List<String> routeList;

    public Robot() {
        routeList = new LinkedList<>();
    }

    public void addRoute(List<String> route) {

        if (!routeList.isEmpty())    {
            String last = routeList.get(routeList.size() - 1);
            String first = route.get(0);

            if (last.equals(first)) {
                routeList.remove(routeList.size() - 1);
            }
        }

        routeList.addAll(route);
    }

    public void move() {
        assert routeList.get(0) != null;

        String[] info = routeList.remove(0).split(",");

        r = Integer.parseInt(info[0]);
        c = Integer.parseInt(info[1]);
    }

    public boolean isMoveable() {
        return !routeList.isEmpty();
    }

    @Override
    public String toString() {
        return "Robot{" +
                "routeList=" + routeList +
                '}';
    }
}

class RobotManager {

    List<Robot> robots;

    public RobotManager(int[][] points, int[][] routes) {
        robots = new ArrayList<>(routes.length);

        Map<String, List<String>> routeMap = new HashMap<>();

        for (int[] route : routes) {

            Robot robot = new Robot();

            for (int i = 0; i < route.length - 1; i++) {

                String point2point = route[i] + ":" + route[i + 1];
                if (!routeMap.containsKey(point2point)) {
                    routeMap.put(point2point, getPath(points, point2point));
                }

                robot.addRoute(routeMap.get(point2point));
            }

            robots.add(robot);
        }
    }

    private List<String> getPath(int[][] points, String point2point) {
        List<String> path = new LinkedList<>();

        String[] info = point2point.split(":");
        int[] start = points[Integer.parseInt(info[0]) - 1];
        int[] end = points[Integer.parseInt(info[1]) - 1];

        int startR = start[0];
        int startC = start[1];
        int endR = end[0];
        int endC = end[1];

        while (startR != endR || startC != endC) {

            path.add(startR + "," + startC);

            if (startR != endR) {
                startR += startR < endR ? 1 : -1;
            } else {
                startC += startC < endC ? 1 : -1;
            }
        }

        path.add(endR + "," + endC);

        return path;
    }

    public void moveRobots() {

        List<Robot> moveableRobots = robots.stream()
                .filter(Robot::isMoveable)
                .collect(Collectors.toList());

        for (Robot robot : moveableRobots) {
            robot.move();
        }

        robots = moveableRobots;
    }

    public int getCollisions() {

        Map<String, Integer> mapper = new HashMap<>();

        for (Robot robot : robots) {
            String cord = robot.r + "," + robot.c;
            mapper.put(cord, mapper.getOrDefault(cord, 0) + 1);
        }

        int count = 0;
        for (String key : mapper.keySet()) {
            if (mapper.get(key) > 1) {
                count++;
            }
        }

        return count;
    }

    public boolean isFinished() {
        return robots.isEmpty();
    }
}
