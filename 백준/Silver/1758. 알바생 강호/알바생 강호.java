import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static PriorityQueue<Integer> pq;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long answer = 0;
        int i = 0;

        while (!pq.isEmpty()) {

            int curr = pq.poll();
            int tip = curr - i++;

            if (tip < 0) {
                break;
            }

            answer += tip;
        }

        System.out.println(answer);
    }
}
