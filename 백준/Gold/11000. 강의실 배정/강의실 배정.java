import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static PriorityQueue<Point> points;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        points = new PriorityQueue<>(N);
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            points.add(new Point(s, Type.IN));
            points.add(new Point(t, Type.OUT));
        }
    }


    public static void main(String[] args) throws IOException {

        init();

        int answer = 1;

        int cntIn = 0;
        int cntOut = 0;
        while (!points.isEmpty())   {
            Point current = points.poll();

            if (current.type == Type.IN) {
                cntIn++;
            }
            else if (current.type == Type.OUT) {
                cntOut++;
            }

            answer = Math.max(
                    answer, Math.abs(cntIn - cntOut)
            );
        }

        System.out.println(answer);
    }

}

enum Type {
    IN, OUT;
}

class Point implements Comparable<Point> {

    int time;
    Type type;

    public Point(int time, Type type) {
        this.time = time;
        this.type = type;
    }

    @Override
    public int compareTo(Point o) {
        if (this.time != o.time) {
            return Integer.compare(this.time, o.time);
        }

        return this.type == o.type ? 0 :
                this.type == Type.OUT ? -1 : 1;
    }

    @Override
    public String toString() {
        return "Point{" +
               "time=" + time +
               ", type=" + type +
               '}';
    }
}
