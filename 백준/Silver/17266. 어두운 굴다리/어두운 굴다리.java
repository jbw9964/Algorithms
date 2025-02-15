import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] lights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int maxDist = Integer.MIN_VALUE;
        for (int i = 1; i < M; i++) {
            maxDist = Math.max(
                    maxDist, Math.abs(lights[i] - lights[i - 1])
            );
        }

        int minima1 = maxDist / 2 + (maxDist % 2 == 0 ? 0 : 1);
        int minima2 = Math.max(
                lights[0], N - lights[M - 1]
        );

        System.out.println(Math.max(minima1, minima2));
    }
}