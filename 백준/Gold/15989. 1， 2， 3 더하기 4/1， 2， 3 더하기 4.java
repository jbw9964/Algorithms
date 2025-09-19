import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final int MAXIMA = 10_000;
    private static int[] DP;

    private static void init() throws IOException {

        DP = new int[MAXIMA + 1];
        Arrays.fill(DP, 1);

        for (int num = 2; num <= MAXIMA; num++) {
            DP[num] += DP[num - 2];
        }

        for (int num = 3; num <= MAXIMA; num++) {
            DP[num] += DP[num - 3];
        }
    }

    public static void main(String[] args) throws IOException {

        init();
        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            answer.append(DP[N]).append("\n");
        }

        System.out.println(answer.toString());
    }
}
