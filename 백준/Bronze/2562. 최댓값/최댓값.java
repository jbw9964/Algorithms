import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final int NUM = 9;

    public static void main(String[] args) throws IOException {
        
        int[] array = new int[NUM];

        for (int i = 0; i < NUM; i++)   array[i] = Integer.valueOf(br.readLine());

        int maxima = array[0];
        int maxIndex = 1;
        for (int i = 1; i < NUM; i++) {
            if (array[i] >= maxima) {
                maxima = array[i];
                maxIndex = i + 1;
            }
        }

        System.out.println(maxima + "\n" + maxIndex);
    }
}