import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    private static boolean[] Table;     // false : prime number 
    
    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Table = new boolean[N + 1];
        Table[0] = Table[1] = true;

        int count = 0;
        for (int i = 2; i <= N; i++) {

            if (Table[i])       continue;

            for (int j = i; j <= N; j += i) {
                if (Table[j])   continue;
                Table[j] = true;
                count += 1;

                if (count == K) {sb.append(j); break;}
            }
        }

        System.out.println(sb.toString());
    }
}
