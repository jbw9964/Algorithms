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
        br.readLine();

        String[] inputStrings = br.readLine().split("");

        int sum = 0;

        for (String value : inputStrings) {
            sum += Integer.parseInt(value);
        }

        bw.write(String.valueOf(sum));
        bw.flush();
    }
}
