import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final int MAXIMA = 10_000;
    private static int[][] DP;

    private static void init() throws IOException {

        DP = new int[MAXIMA + 1][2];
        DP[2][0] = DP[3][0] = DP[3][1] = 1;

        for (int num = 4; num <= MAXIMA; num++) {

            DP[num][0] = 1 + DP[num - 2][0];
            DP[num][1] = 1 + DP[num - 3][0] + DP[num - 3][1];

        }
    }


    public static void main(String[] args) throws IOException {

        init();
        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {

            int N = Integer.parseInt(br.readLine());

            int sum = 1 + DP[N][0] + DP[N][1];
            answer.append(sum).append("\n");
        }

        System.out.println(answer.toString());
    }
}
