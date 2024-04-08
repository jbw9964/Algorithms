import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main (String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] array = new int[N];

        for (int i = 0; i < N; i++)     array[i] = Integer.parseInt(br.readLine());

        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            int number = array[i];

            if (number > K)     continue;

            int quotient = K / number;
            int modulo = K - quotient * number;

            count += quotient;

            if (modulo == 0)    break;

            K = modulo;
        }

        System.out.println(count);
    }
}
