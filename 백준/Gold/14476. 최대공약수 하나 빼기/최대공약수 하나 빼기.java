import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static long[] numbers;
    private static long[] prefixedGcdFromLeft, prefixedGcdFromRight;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        prefixedGcdFromLeft = new long[N];
        prefixedGcdFromLeft[0] = numbers[0];

        prefixedGcdFromRight = new long[N];
        prefixedGcdFromRight[N - 1] = numbers[N - 1];

        for (int i = 1; i < N; i++) {
            prefixedGcdFromLeft[i] = gcd(prefixedGcdFromLeft[i - 1], numbers[i]);
        }
        for (int i = N - 2; i >= 0; i--) {
            prefixedGcdFromRight[i] = gcd(prefixedGcdFromRight[i + 1], numbers[i]);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        long gcdAnswer, kAnswer;

        if (numbers[0] % prefixedGcdFromRight[1] != 0)  {
            gcdAnswer = prefixedGcdFromRight[1];
            kAnswer = numbers[0];
        }
        else if (numbers[N - 1] % prefixedGcdFromLeft[N - 2] != 0)  {
            gcdAnswer = prefixedGcdFromLeft[N - 2];
            kAnswer = numbers[N - 1];
        }
        else {
            gcdAnswer = kAnswer = -1;
        }

        for (int i = 1; i < N - 1; i++) {

            long removingNumber = numbers[i];
            long gcd = gcd(prefixedGcdFromLeft[i - 1], prefixedGcdFromRight[i + 1]);

            if (
                    gcdAnswer < gcd &&
                    removingNumber % gcd != 0
            )   {
                gcdAnswer = gcd;
                kAnswer = removingNumber;
            }
        }


        if (gcdAnswer != -1)    {
            System.out.printf("%d %d\n", gcdAnswer, kAnswer);
        }
        else {
            System.out.println(-1);
        }
    }

    private static long gcd(long bigger, long smaller) {

        if (bigger < smaller)   {
            bigger += smaller;
            smaller = bigger - smaller;
            bigger -= smaller;
        }

        long mod = bigger % smaller;

        return mod == 0 ? smaller : gcd(smaller, mod);
    }
}
