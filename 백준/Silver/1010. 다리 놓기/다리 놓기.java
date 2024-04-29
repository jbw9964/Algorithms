import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    private static int[][] nCr;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        // nCr = (n - 1)C(r - 1) + (n - 1)Cr
        nCr = new int[30][30];

        for (int n = 1; n < 30; n++)    {
            for (int r = 0; r <= n; r++)    {
                int value;

                if (r == 0 || r == n)           value = 1;
                else if (r == 1 || r == n - 1)  value = n;
                else                            value = nCr[n - 1][r - 1] + nCr[n - 1][r];

                nCr[n][r] = value;
            }
        }

        while (T-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());

            sb.append(nCr[M][N]).append("\n");
        }

        System.out.print(sb.toString());
    }
}