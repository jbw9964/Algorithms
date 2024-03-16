import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );
    
    private static String lineString = null;

    public static void main(String[] args) throws IOException {
        
        int maxima = 0;
        int[] maximumIndex = {0, 0};
        
        for (int i = 0; i < 9; i++) {
            lineString = br.readLine();
            StringTokenizer tokenizer = new StringTokenizer(lineString);

            for (int j = 0; j < 9; j++) {
                int element = Integer.parseInt(tokenizer.nextToken());
                if (element >= maxima) {
                    maxima = element;
                    maximumIndex[0] = i + 1;
                    maximumIndex[1] = j + 1;
                }
            }
        }

        System.out.println(maxima + "\n" + maximumIndex[0] + " " + maximumIndex[1]);
    }
}