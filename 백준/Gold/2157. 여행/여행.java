import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int N, M, K;
    private static int[][] costTable;
    private static int[][] DP;

    private static void input() throws IOException  {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        costTable = new int[N + 1][N + 1];
        DP = new int[M + 1][N + 1];

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            costTable[a][b] = costTable[a][b] < c ? c : costTable[a][b];
        }

        for (int i = 2; i <= N; i++)
        DP[2][i] = costTable[1][i];
    }
    
    public static void main(String[] args) throws IOException {
        input();

        for (int currentCity = 3; currentCity <= N; currentCity++)   {
            for (int visit = 3; visit <= currentCity && visit <= M; visit++)    {

                for (int prevCity = visit - 1; prevCity < currentCity; prevCity++) {
                    int costPrevToCurrent = costTable[prevCity][currentCity];
                    int costSumToPrev = DP[visit - 1][prevCity];

                    if (costPrevToCurrent == 0 || costSumToPrev == 0)      continue;

                    DP[visit][currentCity] = Math.max(DP[visit][currentCity], costPrevToCurrent + costSumToPrev);
                }
            }
        }

        int maxima = 0;
        for (int i = 1; i <= M; i++)
        if (maxima < DP[i][N])  maxima = DP[i][N];

        System.out.print(maxima);
    }
}
