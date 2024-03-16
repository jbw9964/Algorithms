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
            appendBlank(line - 1);
            appendStar(length - 2 * line + 2);
            STR.append('\n');
        }

        for (int line = 1; line < N + 1; line++) {
            appendBlank(N - line);
            appendStar(2 * line - 1);
            STR.append('\n');
        }

        System.out.println(STR.toString());
    }

    public static void appendBlank(int num) {
        for (int i = 0; i < num; i++)   STR.append(' ');
    }

    public static void appendStar(int num) {
        for (int i = 0; i < num; i++)   STR.append('*');
    }
}