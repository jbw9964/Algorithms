import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int minima = Integer.MAX_VALUE;

        for (int i = 0; true; i++)  {
            int num = N - 5 * i;

            if (num <= 0)   {
                if (num == 0)
                minima = Math.min(minima, i);
                break;
            }

            if (num % 3 == 0)
            minima = Math.min(minima, i + num / 3);
        }

        System.out.println(minima == Integer.MAX_VALUE ? -1 : minima);
    }
}
