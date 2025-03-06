import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final int MAX = 1_000_000;
    private static int N;
    private static int[] parents;
    private static int[] counts;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        parents = IntStream.range(0, MAX).toArray();
        counts = new int[MAX];
        Arrays.fill(counts, 1);
    }

    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        init();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            char op = st.nextToken().charAt(0);
            int v1 = Integer.parseInt(st.nextToken()) - 1;

            if (op == 'Q') {
                sb.append(counts[find(v1)])
                        .append("\n");
                continue;
            }

            int v2 = Integer.parseInt(st.nextToken()) - 1;

            int p1 = find(v1);
            int p2 = find(v2);

            if (p1 == p2)   {
                continue;
            }

            int root = Math.min(p1, p2);
            int child = Math.max(p1, p2);

            parents[child] = root;
            counts[root] += counts[child];
        }

        System.out.print(sb.toString());
    }

    private static int find(int v)  {
        return v == parents[v] ? v : (parents[v] = find(parents[v]));
    }
}
