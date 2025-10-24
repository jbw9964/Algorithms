import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] memUsages, reuseCosts;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memUsages = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        reuseCosts = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        int MAX_COST = Arrays.stream(reuseCosts).sum();
        int[] DP = new int[MAX_COST + 1];

        for (int i = 0; i < N; i++) {
            int baseMem = memUsages[i];
            int baseCost = reuseCosts[i];

            for (int cost = MAX_COST; cost >= baseCost; cost--) {
                DP[cost] = Math.max(
                        DP[cost],
                        DP[cost - baseCost] + baseMem
                );
            }
        }

        int answer = IntStream.rangeClosed(0, MAX_COST)
                .filter(cost -> DP[cost] >= M)
                .findFirst()
                .orElseThrow(RuntimeException::new);

        System.out.println(answer);
    }
}
