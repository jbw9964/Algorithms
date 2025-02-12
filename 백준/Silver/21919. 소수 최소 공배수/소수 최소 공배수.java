import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final boolean[] primeTable = new boolean[1_000_000 + 1];

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        initPrimes();

        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Set<Integer> primes = new HashSet<>(N);
        for (int num : numbers) {
            if (isPrime(num)) {
                primes.add(num);
            }
        }

        long ans = 1;
        for (int prime : primes) {
            ans *= prime;
        }

        System.out.println(primes.isEmpty() ? -1 : ans);
    }

    private static void initPrimes()    {
        int n = primeTable.length - 1;

        recordAsPrime(2);
        int sqrt = (int) Math.sqrt(n);

        for (int num = 2; num <= sqrt; num++) {

            if (!isPrime(num)) {
                continue;
            }

            for (int mul = 2; mul * num <= n; mul++) {
                recordAsNonPrime(mul * num);
            }
        }
    }

    private static boolean isPrime(int n) {
        return !primeTable[n];
    }

    private static void recordAsNonPrime(int n) {
        primeTable[n] = true;
    }

    private static void recordAsPrime(int n) {
        primeTable[n] = false;
    }
}