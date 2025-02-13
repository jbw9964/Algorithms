import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static final int IN = 1, OUT = 0;

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            pq.add(new Point(start, IN));
            pq.add(new Point(end, OUT));
        }

        int maxima = 0, cnt = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();

            if (curr.type == IN) {
                maxima = Math.max(maxima, ++cnt);
            } else {
                cnt--;
            }
        }

        System.out.println(maxima);
    }
}

class Point implements Comparable<Point> {

    long time;
    int type;

    public Point(long time, int type) {
        this.time = time;
        this.type = type;
    }

    // fastest & OUT first
    @Override
    public int compareTo(Point o) {
        return time != o.time ? Long.compare(time, o.time) :
                type - o.type;
    }

    @Override
    public String toString() {
        return "Point{" +
                "time=" + time +
                ", type=" + (type == Main.IN ? "IN" : "OUT") +
                '}';
    }
}