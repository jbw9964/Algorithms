import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] healths, joys;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        healths = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        joys = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[100];

        for (int i = 0; i < N; i++) {

            int health = healths[i];
            int joy = joys[i];

            for (int allocatedHealth = 100 - 1; allocatedHealth >= health; allocatedHealth--) {

                DP[allocatedHealth] = Math.max(
                        DP[allocatedHealth],
                        DP[allocatedHealth - health] + joy
                );

            }
        }

        System.out.println(Arrays.stream(DP).max().getAsInt());
    }

}
