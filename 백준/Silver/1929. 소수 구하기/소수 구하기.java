import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int[] primeArray;
    private static int[] sieveOfEratosthenes;


    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(tokenizer.nextToken());
        int N = Integer.parseInt(tokenizer.nextToken());

        // primeArray = DPMethod1(M, N);
        sieveOfEratosthenes = DPMethod2(M, N);


        showAnswer(sieveOfEratosthenes, M, N);
    }

    public static int[] DPMethod1(int M, int N) {
        int[] primeArray = new int[N];

        for (int value = 2; value <= N; value++) {
            int index;
            boolean primeFlag = true;

            for (index = 0; index < N; index++) {
                if (primeArray[index] == 0)                 break;
                else if (value % primeArray[index] == 0)    {
                    primeFlag = false;
                    break;
                }
            }

            if (primeFlag)  primeArray[index] = value;
        }

        return primeArray;
    }

    public static int[] DPMethod2(int M, int N) {
        int[] primeArray = new int[N];
        int index = 0;

        boolean[] sieveOfEratosthenes = new boolean[N + 1];     // true : not a prime number
        sieveOfEratosthenes[0] = true;
        sieveOfEratosthenes[1] = true;

        for (int number = 2; number <= N; number++) {
            if (sieveOfEratosthenes[number]) continue;

            primeArray[index++] = number;
            for (int i = 2;; i++) {
                if (number * i > N) break;

                sieveOfEratosthenes[number * i] = true;
            }
        }

        return primeArray;
    }

    public static void showAnswer(int[] array, int M, int N) {
        for (int value : array) {
            if (M <= value && value <= N)   System.out.println(value);
        }
    }
}