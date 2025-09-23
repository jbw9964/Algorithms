import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, K;
    private static Cord[] cords;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cords = new Cord[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            cords[i] = new Cord(x, y);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int[][] DP = new int[K + 1][N];

        for (int i = 1; i < N; i++) {
            Cord prev = cords[i - 1];
            Cord curr = cords[i];

            DP[0][i] = DP[0][i - 1] + prev.getDistFrom(curr);
        }

        for (int k = 1; k <= K; k++) {

            DP[k][k] = DP[0][k];

            for (int curr = k + 1; curr < N; curr++) {

                Cord currCord = cords[curr];
                int minima = Integer.MAX_VALUE;

                int prevK = 0;
                for (int prev = curr - k - 1; prev < curr; prev++) {
                    Cord prevCord = cords[prev];

                    int dist = currCord.getDistFrom(prevCord);
                    dist += DP[prevK++][prev];

                    minima = Math.min(minima, dist);
                }

                DP[k][curr] = minima;
            }
        }

        int answer = DP[K][N - 1];

        System.out.println(answer);
    }
}

class Cord {

    final int x, y;

    public Cord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getDistFrom(Cord cord) {
        return Math.abs(x - cord.x) + Math.abs(y - cord.y);
    }

    @Override
    public String toString() {
        return "Cord{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}
