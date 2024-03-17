import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String[] numArray = br.readLine().split(" ");
        
        int maxima = -1_000_000;
        int minima = 1_000_000;

        for (String element : numArray) {
            int value = Integer.parseInt(element);

            if (value > maxima) maxima = value;
            if (value < minima) minima = value;
        }

        System.out.println(minima + " " + maxima);
    }
}