import java.io.*;
import java.math.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] numbers;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        StringBuilder answer = new StringBuilder();

        int max = new BigDecimal(N).pow(M).intValueExact();
        for (int i = 0; i < max; i++) {

            int val = i;
            int div = max / N;

            for (int j = 0; j < M; j++) {
                int index = val / div;

                answer.append(numbers[index]).append(" ");

                val %= div;
                div /= N;
            }

            answer.append("\n");
        }

        System.out.print(answer.toString());
    }
}
