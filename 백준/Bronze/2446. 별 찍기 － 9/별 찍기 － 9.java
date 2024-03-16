import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder STR = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int length = 2 * N - 1;

        for (int line = 1; line < N; line++) {
            STR.append(" ".repeat(line - 1))
                .append("*".repeat(length - 2 * line + 2))
                .append('\n');
        }

        for (int line = 1; line < N + 1; line++) {
            STR.append(" ".repeat(N - line))
                .append("*".repeat(2 * line - 1))
                .append('\n');
        }

        System.out.println(STR.toString());
    }
}