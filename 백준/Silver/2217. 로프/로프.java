import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] ropes;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        ropes = new int[N];
        for (int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes);
    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = ropes[0] * N;

        for (int i = 1; i < N; i++) {
            int numOfRopes = N - i;
            answer = Math.max(answer, ropes[i] * numOfRopes);
        }

        System.out.println(answer);
    }
}
