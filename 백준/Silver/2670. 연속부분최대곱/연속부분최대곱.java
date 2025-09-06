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

        double answer = 0;
        double[] DP = new double[N];
        DP[0] = numbers[0];

        for (int i = 1; i < N; i++) {
            double num = numbers[i];
            DP[i] = Math.max(num, num * DP[i - 1]);
            answer = Math.max(answer, DP[i]);
        }

        System.out.printf("%.3f\n", answer);
    }
}
