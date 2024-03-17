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

    public static void main(String[] args) throws IOException {
        String[] inputStrings = br.readLine().split("");

        int count = 1;
        for (String character : inputStrings) {
            bw.write(character);
            if (count++ % 10 == 0)  bw.newLine();
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
