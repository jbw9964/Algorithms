import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Point> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();

            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            pq.add(new Point(start, Type.IN));
            pq.add(new Point(end, Type.OUT));
        }

        int maxima = 0, cnt = 0;
        while (!pq.isEmpty()) {
            Point curr = pq.poll();

            if (curr.type == Type.IN) {
                maxima = Math.max(maxima, ++cnt);
            } else {
                cnt--;
            }
        }

        System.out.println(maxima);
    }
}

enum Type {
    IN, OUT
}

class Point implements Comparable<Point> {

    long time;
    Type type;

    public Point(long time, Type type) {
        this.time = time;
        this.type = type;
    }

    // fastest & OUT first
    @Override
    public int compareTo(Point o) {
        return time != o.time ? Long.compare(time, o.time) :
                type == Type.OUT ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Point{" +
                "time=" + time +
                ", type=" + type +
                '}';
    }
}
