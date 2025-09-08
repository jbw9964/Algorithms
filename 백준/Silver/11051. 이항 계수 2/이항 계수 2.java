import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, R;
    private static final int MOD = 10_007;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

    }

    public static void main(String[] args) throws IOException {

        init();

        int[] DP = new int[N + 1];
        DP[0] = 1;

        for (int n = 1; n <= N; n++) {
            for (int r = n; r >= 1; r--) {
                DP[r] = (DP[r - 1] + DP[r]) % MOD;
            }
        }

        System.out.println(DP[R]);
    }
}

/*

(n+1)C(r+1) = nCr + nC(r+1)
nCr = (n-1)C(r-1) + (n-1)Cr

 */