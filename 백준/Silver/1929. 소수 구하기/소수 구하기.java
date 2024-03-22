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

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        int[] primeArray = new int[N];
        int prime_count = 0;

        for (int number = 2; number <= N; number++) {

            boolean flag = true;
            for (int i = 0; i < prime_count; i++) {

                if (primeArray[i] > Math.sqrt(number)) break;

                else if (number % primeArray[i] == 0) {
                    flag = false;
                    break;
                }
                
            }

            if (flag)   primeArray[prime_count++] = number;
        }

        for (int i = 0; i < prime_count; i++) {
            int value = primeArray[i];

            if (M <= value && value <= N)   System.out.println(value);
        }
    }
}