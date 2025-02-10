import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static BigDecimal K;
    private static BigDecimal[][] combinations;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = new BigDecimal(Integer.parseInt(st.nextToken()));

        combinations = new BigDecimal[N + M + 1][M + M + 1];
        combinations[1][1] = combinations[1][0] = new BigDecimal(1);
    }

    private static BigDecimal getCombination(int n, int m)  {
        if (m == 0 || m == n) {
            return BigDecimal.ONE;
        }

        return combinations[n][m] != null ? combinations[n][m] :
                (combinations[n][m] = getCombination(n - 1, m - 1).add(getCombination(n - 1, m)));
    }

    public static void main(String[] args) throws IOException {

        init();

        System.out.println(ans());
    }

    private static String ans() {
        if (K.compareTo(getCombination(N + M, Math.min(N, M))) > 0) {
            return "-1";
        }

        StringBuilder sb = new StringBuilder();

        BigDecimal k = new BigDecimal(String.valueOf(K));
        int numOfA = N;
        int numOfZ = M;

        while (numOfA > 0 && numOfZ > 0)    {

            BigDecimal cnt = getCombination(numOfA + numOfZ - 1, Math.min(numOfA - 1, numOfZ));

            if (k.compareTo(cnt) <= 0) {
                numOfA--;
                sb.append("a");
            }
            else {
                numOfZ--;
                sb.append("z");
                k = k.subtract(cnt);
            }
        }

        while (numOfA-- > 0)    {
            sb.append("a");
        }
        while (numOfZ-- > 0)    {
            sb.append("z");
        }

        return sb.toString();
    }
}
