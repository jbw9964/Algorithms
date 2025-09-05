import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static long N, R, C;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
    }


    public static void main(String[] args) throws IOException {

        init();

        long answer = 0;

        while (N-- > 0) {

            Region region = examineRegion(N + 1, R, C);

            long halfTableLength = qudOf(N);
            long add = halfTableLength * halfTableLength;

            if (region == Region.UP_RIGHT) {
                answer += add;
                C -= halfTableLength;
            } else if (region == Region.DOWN_LEFT) {
                answer += 2 * add;
                R -= halfTableLength;
            } else if (region == Region.DOWN_RIGHT) {
                answer += 3 * add;
                C -= halfTableLength;
                R -= halfTableLength;
            }
        }

        System.out.println(answer);
    }

    private static Region examineRegion(long n, long r, long c) {

        long tableLength = qudOf(n);
        boolean up = r < tableLength / 2L;
        boolean left = c < tableLength / 2L;

        Region region;

        if (up) {
            region = left ? Region.UP_LEFT : Region.UP_RIGHT;
        } else {
            region = left ? Region.DOWN_LEFT : Region.DOWN_RIGHT;
        }

        return region;
    }

    private static long qudOf(long n) {

        long val = 1;
        while (n-- > 0) {
            val <<= 1;
        }

        return val;
    }
}

enum Region {
    UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT;
}
