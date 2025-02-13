import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        List<Info> infos = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int index = Integer.parseInt(st.nextToken());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            infos.add(new Info(index, start, end));
        }

        // get latest-start lectures
        infos.sort(Comparator.comparing(i -> i.start));

        // get first-end lectures
        PriorityQueue<Info> pq = new PriorityQueue<>(Comparator.comparing(i -> i.end));

        int ans = 0;
        for (Info curr : infos) {

            Info firstEnd = pq.peek();

            if (firstEnd!=null && curr.start>=firstEnd.end)   {
                pq.poll();
            }

            pq.add(curr);
            ans = Math.max(ans, pq.size());
        }

        System.out.println(ans);
    }

}

class Info {
    int index;
    long start, end;

    public Info(int index, long start, long end) {
        this.index = index;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "Info{" +
                "index=" + index +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
