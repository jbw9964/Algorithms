import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 9; i++) {
            System.out.printf(
                "%s * %s = %s\n",
                N, i, N * i
            );
        }
    }
}
