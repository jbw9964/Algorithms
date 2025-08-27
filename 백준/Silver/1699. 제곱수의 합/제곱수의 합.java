import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] DP;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        DP = new int[N + 2];
        DP[1] = 1;
        DP[2] = 2;

        for (int num = 3; num <= N; num++) {

            if (sqrtAble(num))  {
                DP[num] = 1;
                continue;
            }

            int minima = num;
            for (int i = 1; i <= num / 2; i++) {
                int head = DP[i];
                int tail = DP[num - i];
                minima = Math.min(minima, head + tail);
            }

            DP[num] = minima;
        }
    }

    private static boolean sqrtAble(long num)   {
        long sqrt = (long) Math.sqrt(num);
        return sqrt * sqrt == num;
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(DP[N]);
    }
}
