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
        String[] inpuStrings = br.readLine().split(" ");

        bw.write(String.valueOf(
            Integer.parseInt(inpuStrings[0]) + Integer.parseInt(inpuStrings[1])
        ));

        bw.flush();
    }
}
