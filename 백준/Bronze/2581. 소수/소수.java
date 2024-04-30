import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static boolean[] primeTable;

    public static void main(String[] args) throws IOException {
        int M = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());

        primeTable = new boolean[N + 1];            // false : prime number
        primeTable[0] = primeTable[1] = true;

        for (int i = 2; i <= Math.sqrt(N); i++)    {
            if (primeTable[i])      continue;

            for (int j = 2 * i; j <= N; j += i)
            primeTable[j] = true;
        }

        int minima = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = M; i <= N; i++)    {
            if (primeTable[i])      continue;
            
            if (sum == 0)           minima = i;
            sum += i;
        }

        if (sum == 0)   System.out.println(-1);
        else            System.out.println(sum + "\n" + minima);
    }
    
}