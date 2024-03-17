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
        
        for (int i = 0; i < N - 1; i++) {
            STR.append("*".repeat(i + 1))
                .append(" ".repeat(2 * N - 2 * (i + 1)))
                .append("*".repeat(i + 1))
                .append("\n");
        }

        STR.append("*".repeat(2 * N)).append("\n");

        for (int i = N - 2; i >= 0; i--) {
            STR.append("*".repeat(i + 1))
                .append(" ".repeat(2 * N - 2 * (i + 1)))
                .append("*".repeat(i + 1))
                .append("\n");
        }

        System.out.println(STR.toString());
    }
}