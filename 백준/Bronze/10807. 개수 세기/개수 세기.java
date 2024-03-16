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

        String[] array = br.readLine().split(" ");
        String target = br.readLine().split(" ")[0];

        int count = 0;
        for (String element : array) {
            if (element.equals(target)) count++;
        }

        bw.write(String.valueOf(count));
        bw.flush();
    }
}