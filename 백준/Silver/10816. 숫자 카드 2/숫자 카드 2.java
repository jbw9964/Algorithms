import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static final int MIN = 10_000_000;
    private static int[] cntMap, checks;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        int[] cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        cntMap = new int[2 * MIN + 1];
        for (int i = 0; i < N; i++) {
            cntMap[cards[i] + MIN]++;
        }

        M = Integer.parseInt(br.readLine());
        checks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        StringBuilder sb = new StringBuilder();

        for (int check : checks)    {

            sb.append(cntMap[check + MIN])
                    .append(" ");
        }

        System.out.println(sb.toString());
    }
}
