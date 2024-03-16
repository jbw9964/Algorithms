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
        boolean[] array = new boolean[30];

        for (int i = 0; i < 28; i++) {
            array[Integer.parseInt(br.readLine().split(" ")[0]) - 1] = true;
        }

        for (int i = 0; i < 30; i++) {
            if (!array[i])  bw.write(String.valueOf(i + 1) + "\n");
        }

        bw.flush();
    }
}