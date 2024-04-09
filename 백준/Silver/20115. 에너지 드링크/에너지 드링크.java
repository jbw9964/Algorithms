import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main (String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        String[] numbers = br.readLine().split(" ");
        double max = 0;
        double sum = 0;
        for (int i = 0; i < N; i++) {
            double num = Double.parseDouble(numbers[i]);
            if (num > max)  max = num;
            sum+= num;
        }

        System.out.printf("%.6f\n", (sum - max) / 2 + max);
    }
}
