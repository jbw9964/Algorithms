import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    private static class Info {
        int time, cost;
        Info(int time, int cost)    {
            this.time = time;
            this.cost = cost;
        }
    }

    private static Info[] infos;
    private static int maxProfit;

    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );
    

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        infos = new Info[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());

            infos[i] = new Info(
                Integer.parseInt(tokenizer.nextToken()), 
                Integer.parseInt(tokenizer.nextToken())
            );
        }

        compSearch(0, 0);

        System.out.println(maxProfit);
    }

    public static void compSearch(int index, int cost) {
        if (index >= infos.length)  {
            if (maxProfit < cost)   maxProfit = cost;
            return;
        }

        Info current = infos[index];

        for (int i = index + current.time; i <= infos.length; i++)
        compSearch(i, cost + current.cost);

        compSearch(index + 1, cost);

        return;
    }
}
