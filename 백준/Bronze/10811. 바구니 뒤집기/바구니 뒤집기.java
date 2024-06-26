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
        String[] linstStrings = br.readLine().split(" ");
        int N = Integer.parseInt(linstStrings[0]);
        int M = Integer.parseInt(linstStrings[1]);

        String[] array = new String[N];
        for (int i = 0; i < N; i++) array[i] = String.valueOf(i + 1);

        String tempString;

        for (int count = 0; count < M; count++) {
            linstStrings = br.readLine().split(" ");

            int i = Integer.parseInt(linstStrings[0]) - 1;
            int j = Integer.parseInt(linstStrings[1]) - 1;

            if (i == j) continue;

            while (i < j) {
                tempString = array[i];
                array[i++] = array[j];
                array[j--] = tempString;
            }

        }

        for (String element : array) {
            bw.write(element + " ");
        }

        bw.flush();
    }
}