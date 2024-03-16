import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        String[] lineStrings = br.readLine().split(" ");
        int N = Integer.parseInt(lineStrings[0]);
        int X = Integer.parseInt(lineStrings[1]);

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int value;
        
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if ((value = Integer.parseInt(tokenizer.nextToken())) < X) {
                result.append(value).append(" ");
            }
        }

        System.out.println(result);
    }
}