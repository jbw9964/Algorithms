import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K, W;
    private static int[] costs;
    private static List<Integer>[] reverseAdjacent;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        costs = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //noinspection unchecked
        reverseAdjacent = IntStream.range(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            reverseAdjacent[to].add(from);
        }

        W = Integer.parseInt(br.readLine());
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {
            init();

            int[] table = new int[N];
            table[W - 1] = costs[W - 1];

            Queue<Integer> q = new ArrayDeque<>();
            q.add(W - 1);

            while (!q.isEmpty()) {
                int curr = q.poll();
                int tableVal = table[curr];

                for (int adjacent : reverseAdjacent[curr]) {
                    int val = tableVal + costs[adjacent];
                    if (val > table[adjacent]) {
                        table[adjacent] = val;
                        q.add(adjacent);
                    }
                }
            }

            int maxima = Arrays.stream(table)
                    .max()
                    .orElseThrow(RuntimeException::new);

            answer.append(maxima).append("\n");
        }

        System.out.println(answer.toString());
    }
}
