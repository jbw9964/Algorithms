import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] ratings;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        ratings = new int[N];
        for (int i = 0; i < N; i++) {
            ratings[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ratings);
    }

    public static void main(String[] args) throws IOException {

        init();

        int trunc = Math.round(N * 0.15f);

        int sum = 0;
        for (int i = trunc; i < N - trunc; i++) {
            sum += ratings[i];
        }

        System.out.println(Math.round(sum / (double) (N - 2 * trunc)));
    }
}