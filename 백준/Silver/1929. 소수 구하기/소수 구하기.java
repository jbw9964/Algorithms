import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        boolean[] primeArray = new boolean[N + 1];  // false : prime number
        primeArray[0] = primeArray[1] = true;

        for (int i = 2; i <= N; i++) {
            if (primeArray[i])  continue;

            if (M <= i)         sb.append(String.valueOf(i)).append("\n");

            for (int j = 2 * i; j <= N; j += i) primeArray[j] = true;
        }
        
        
        System.out.println(sb.toString());
    }
}