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

        String input;
        String[] strings;
        int num1, num2;

        while ((input = br.readLine()) != null) {
            strings = input.split(" ");

            num1 = Integer.parseInt(strings[0]);
            num2 = Integer.parseInt(strings[1]);

            bw.write(String.valueOf(num1 + num2) + "\n");
        }

        bw.flush();
    }
}
