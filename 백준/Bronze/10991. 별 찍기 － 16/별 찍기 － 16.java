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

        for (int i = 1; i < N + 1; i++) {
            Main.appendBlank(N - i);
            STR.append("*");

            if (N - i != length - (N - i + 1)) {

                int count = 0;
                int index = 0;
                while (count < i - 1) {
                    
                    if (index++ % 2 != 0)   {
                        STR.append("*");
                        count++;
                        continue;
                    }
                    
                    STR.append(' ');
                }
            }

            STR.append('\n');
        }

        System.out.println(STR.toString());
    }

    public static void appendBlank(int num) {
        for (int i = 0; i < num; i++) STR.append(' ');
    }
}