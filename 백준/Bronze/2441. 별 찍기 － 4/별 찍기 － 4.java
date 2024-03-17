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
        
        for (int i = 0; i < N; i++) {
            STR.append(" ".repeat(i))
                .append("*".repeat(N - i))
                .append("\n");
        }

        System.out.println(STR.toString());
    }
}