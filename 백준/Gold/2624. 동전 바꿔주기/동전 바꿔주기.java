import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int T, K;

    private static int[] numOfCasesInCost;
    private static int[] priceTable, coinNumTable;

    private static void init() throws IOException {
        T = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        numOfCasesInCost = new int[T + 1];

        priceTable = new int[K];
        coinNumTable = new int[K];

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            priceTable[i] = Integer.parseInt(st.nextToken());
            coinNumTable[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        numOfCasesInCost[0] = 1;

        for (int i = 0; i < K; i++) {

            int coinPrice = priceTable[i];
            int maxCoinNum = coinNumTable[i];

            for (int price = T; price >= coinPrice; price--) {
                for (int n = 1; n <= maxCoinNum; n++) {

                    int prevPrice = price - coinPrice * n;

                    if (prevPrice < 0)  {
                        break;
                    }

                    numOfCasesInCost[price] += numOfCasesInCost[prevPrice];
                }
            }
        }

        System.out.println(numOfCasesInCost[T]);
    }
}
