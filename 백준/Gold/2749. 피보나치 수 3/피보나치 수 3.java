import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final int MOD_NUM = 1_000_000;


    public static void main (String[] args) throws IOException {
        long N = Long.parseLong(br.readLine()) % 1_500_000;

        int fn = 1;

        if (N > 1)  {
            int fn2 = 0;
            int fn1 = 1;

            long count = 1;
            while (count++ < N)   {
                fn = (fn1 + fn2) % MOD_NUM;
                fn2 = fn1;
                fn1 = fn;
            }
        }

        System.out.println(fn);
    }
}