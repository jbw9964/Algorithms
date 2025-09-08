import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K, Q, M;
    private static Set<Integer> sleeping;
    private static boolean[] attendance;
    private static String[] checkRanges;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) + 2;
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        attendance = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        sleeping = new HashSet<>(K);
        for (int i = 0; i < K; i++) {
            sleeping.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int check = Integer.parseInt(st.nextToken());
            attendance[check] = true;
        }

        checkRanges = new String[M];
        for (int i = 0; i < M; i++) {
            checkRanges[i] = br.readLine();
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int[] prefixSum = new int[N + 1];
        for (int student = 3; student <= N; student++) {

            prefixSum[student] = prefixSum[student - 1];

            if (!attendance[student]) {
                prefixSum[student] += 1;
                continue;
            }

            if (sleeping.contains(student)) {
                attendance[student] = false;
                prefixSum[student] += 1;
                continue;
            }

            for (int next = 2 * student; next <= N; next += student) {
                attendance[next] = true;
            }
        }

        StringBuilder answer = new StringBuilder();
        StringTokenizer st;
        for (String range : checkRanges) {
            st = new StringTokenizer(range);
            int s =  Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            answer.append(prefixSum[e] - prefixSum[s - 1]).append("\n");
        }

        System.out.print(answer.toString());
    }
}
