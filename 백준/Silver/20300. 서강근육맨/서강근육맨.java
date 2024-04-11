import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main (String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        
        long[] costArray = new long[N];

        String[] lineStrings = br.readLine().split(" ");
        for (int i = 0; i < N; i ++ )   costArray[i] = Long.parseLong(lineStrings[i]);

        Arrays.sort(costArray);

        long maxima = costArray[N - 1];
        for (int i = 0, j = (N / 2) * 2 - 1; i < j; i++, j--) {
            long sum = costArray[i] + costArray[j];
            maxima = sum > maxima ? sum : maxima;
        }

        System.out.println(maxima);
    }
}
