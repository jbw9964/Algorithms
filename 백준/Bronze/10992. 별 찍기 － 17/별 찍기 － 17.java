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
        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i < N; i++) {
            printBlank(N - i);
            bw.write("*");

            if (N - i != N + i - 2) {
                printBlank(2 * i - 3);
                bw.write("*");
            }

            bw.newLine();
        }

        Main.printAll(N);

        bw.flush();
    }

    public static void printBlank(int num) throws IOException {
        for (int i = 0; i < num; i++) bw.write(' ');
    }

    public static void printAll(int num) throws IOException {
        for (int i = 0; i < 2 * num - 1; i++) {
            bw.write("*");
        };  bw.newLine();
    }
}