import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K;
    private static Info[] infos;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        infos = new Info[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int walkTime = Integer.parseInt(st.nextToken());
            int walkProfit = Integer.parseInt(st.nextToken());
            int bikeTime = Integer.parseInt(st.nextToken());
            int bikeProfit = Integer.parseInt(st.nextToken());

            infos[i] = new Info(walkTime, walkProfit, bikeTime, bikeProfit);
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        int[][] profitTableOnTime = new int[N][K + 1];
        profitTableOnTime[0][infos[0].walkTime] = infos[0].walkProfit;
        profitTableOnTime[0][infos[0].bikeTime] = Math.max(
                infos[0].bikeProfit,
                profitTableOnTime[0][infos[0].bikeTime]
        );

        for (int i = 1; i < N; i++) {

            int[] prev = profitTableOnTime[i - 1];
            int[] current = profitTableOnTime[i];

            Info info = infos[i];
            int walkTime = info.walkTime, walkProfit = info.walkProfit;
            int bikeTime = info.bikeTime, bikeProfit = info.bikeProfit;

            for (int time = K; time >= 0; time--) {

                if (prev[time] != 0) {

                    int next;

                    if ((next = time + walkTime) <= K) {
                        current[next] = Math.max(current[next], prev[time] + walkProfit);
                    }

                    if ((next = time + bikeTime) <= K) {
                        current[next] = Math.max(current[next], prev[time] + bikeProfit);
                    }
                }
            }
        }

        int answer = Arrays.stream(profitTableOnTime[N - 1]).max()
                .orElseThrow(RuntimeException::new);

        System.out.println(answer);
    }

}

class Info {

    final int walkTime, walkProfit;
    final int bikeTime, bikeProfit;

    public Info(int walkTime, int walkProfit, int bikeTime, int bikeProfit) {
        this.walkTime = walkTime;
        this.walkProfit = walkProfit;
        this.bikeTime = bikeTime;
        this.bikeProfit = bikeProfit;
    }
}
