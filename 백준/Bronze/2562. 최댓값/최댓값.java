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

    private static final int NUM = 9;

    public static void main(String[] args) throws IOException {
        
        int[] maxima = {0, 0};
        int value;

        for (int i = 0; i < NUM; i++) {
            if ((value = Integer.parseInt(br.readLine().split(" ")[0])) > maxima[0]) {
                maxima[0] = value;
                maxima[1] = i + 1;
            }
        }
        
        bw.write(String.valueOf(maxima[0]) + "\n" + String.valueOf(maxima[1]));
        bw.flush();
    }
}