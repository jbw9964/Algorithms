import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        double[] array = new double[N];
        String[] scores = br.readLine().split(" ");

        double maxima = 0;

        for (int i = 0; i < N; i++) {
            array[i] = Double.parseDouble(scores[i]);

            if (array[i] > maxima)  maxima = array[i];
        }

        double sum = 0;
        for (int i = 0; i < N; i++) {
            sum += array[i] / maxima * 100;
        }

        System.out.println(sum / N);
    }
}