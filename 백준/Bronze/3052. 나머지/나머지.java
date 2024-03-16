import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static final int NUM = 42;

    public static void main(String[] args) throws IOException {
        
        HashSet<Integer> table = new HashSet<Integer>();

        for (int count = 0; count < 10; count++) {
            table.add(
                Integer.parseInt(
                    br.readLine().split(" ")[0]
                ) % NUM
            );
        }

        bw.write(String.valueOf(table.size()));
        bw.flush();
    }
}