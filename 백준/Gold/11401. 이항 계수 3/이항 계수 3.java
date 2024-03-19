import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static final long P = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        String[] inputStrings = br.readLine().split(" ");
        long N = Long.parseLong(inputStrings[0]);
        long K = Long.parseLong(inputStrings[1]);

        K = K <= N - K ? K : N - K;

        long U, L = 1;
        if (K == 0)         U = 1;
        else if (K == 1)    U = N;
        else {
            U = factorialModulo(N, N - K + 1);
            L = powerModulo(factorialModulo(K, 1), P - 2);
        }

        bw.write(String.valueOf((U * L) % P));
        bw.flush();
    }

    public static long powerModulo(long x, long n) {
        if (n == 0)             return 1;

        long half = powerModulo(x, n / 2) % P;

        if (n % 2 == 0) return (half * half) % P;
        else            return ((half * half) % P * x % P) % P;
    }

    public static long factorialModulo(long start, long end) {
        long remain = 1;

        for (long value = start; value >= end; value--) {
            remain = (remain % P * value % P) % P;
        }
        
        return remain;
    }
}