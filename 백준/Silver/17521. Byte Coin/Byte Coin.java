import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, W;
    private static int[] prices;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        prices = new int[N];
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long answer = W;

        for (int pivot = 0; pivot < N; pivot++) {

            long currentPrice = prices[pivot];
            if (answer < currentPrice) {
                continue;
            }

            int next = pivot;
            while (next + 1 < N && prices[next] <= prices[next + 1]) {
                next++;
            }

            long buyCnt = answer / currentPrice;
            long sellProfit = buyCnt * prices[next];
            long mod = answer % currentPrice;

            answer = sellProfit + mod;
            pivot = next;
        }

        System.out.println(answer);
    }
}
