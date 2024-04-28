import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] array = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
        array[i] = Integer.parseInt(tokenizer.nextToken());

        int maxima = Integer.MIN_VALUE;
        for (int i = 0; i <= N - K; i++) {
            int sum = 0;

            for (int j = i; j < i + K; j++)
            sum += array[j];
            
            if (maxima < sum)   maxima = sum;
        }

        System.out.println(maxima);
    }
}