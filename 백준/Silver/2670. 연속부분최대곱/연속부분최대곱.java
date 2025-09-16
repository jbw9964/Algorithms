import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static double[] numbers;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        numbers = new double[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Double.parseDouble(br.readLine());
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        double answer = numbers[0];
        double[] DP = new double[N];
        DP[0] = answer;

        for (int i = 1; i < N; i++) {
            DP[i] = Math.max(numbers[i], DP[i - 1] * numbers[i]);
            answer = Math.max(answer, DP[i]);
        }

        System.out.printf("%.3f\n", answer);
    }
}
