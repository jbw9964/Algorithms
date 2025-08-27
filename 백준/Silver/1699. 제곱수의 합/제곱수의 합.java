import java.io.*;
import java.util.*;

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

            DP[num] = num;
            for (int square = 1; num - square * square >= 1; square++) {
                DP[num] = Math.min(
                        DP[num],
                        DP[num - square * square] + 1
                );
            }
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
