import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );


    private static long H;
    private static int Y;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Long.parseLong(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

    }

    public static void main(String[] args) throws IOException {

        init();

        long[] DP = new long[Y + 1];
        DP[0] = H;

        double[] ratios = {
                1.05d, 1.2d, 1.35d
        };

        for (int y = 0; y < Y; y++) {

            long base = DP[y];

            for (int i = 0; i < 3; i++) {

                int nextY = y + 2 * i + 1;
                double ratio = ratios[i];

                if (nextY <= Y) {
                    DP[nextY] = Math.max(DP[nextY], truncate(base * ratio));
                }
            }
        }

        System.out.println(DP[Y]);
    }

    private static long truncate(double x) {
        return (long) x;
    }
}
