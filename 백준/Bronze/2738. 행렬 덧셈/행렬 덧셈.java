import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );
    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    public static void main(String[] args) throws IOException {
        
        String line = br.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line);

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[][] array = new int[N][M];

        for (int count = 0; count < 2; count++) {

            for (int i = 0; i < N; i++) {
                line = br.readLine();
                tokenizer = new StringTokenizer(line);

                for (int j = 0; j < M; j++) {
                    array[i][j] += Integer.parseInt(tokenizer.nextToken());
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                bw.write(String.valueOf(array[i][j]) + " ");
            };  bw.newLine();
        }

        bw.flush();
        // bw.close();
        // br.close();
    }

}
