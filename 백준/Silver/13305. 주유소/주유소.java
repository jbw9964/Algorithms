import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] distArray, costArray;


    public static void main (String[] args) throws IOException {
        
        input();

        int totalCost = 0;
        for (int i = 0; i < N - 1; i++) {
            int currentCost = costArray[i];
            int nextCost = costArray[i + 1];
            int dist = distArray[i];

            totalCost += dist * currentCost;

            if (nextCost > currentCost) costArray[i + 1] = currentCost;
        }

        System.out.println(totalCost);
    }

    public static void input() throws IOException {
        N = Integer.parseInt(br.readLine());

        distArray = new int[N - 1];
        costArray = new int[N];

        StringTokenizer distTokens = new StringTokenizer(br.readLine());
        StringTokenizer costTokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < N - 1; i++) {
            distArray[i] = Integer.parseInt(distTokens.nextToken());
            costArray[i] = Integer.parseInt(costTokens.nextToken());
        }
        costArray[N - 1] = Integer.parseInt(costTokens.nextToken());
    }
}