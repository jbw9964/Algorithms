import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        String[] linstStrings = br.readLine().split(" ");
        int N = Integer.parseInt(linstStrings[0]);
        int M = Integer.parseInt(linstStrings[1]);

        int temp_value;

        int[] array = new int[N];
        for (int i = 0; i < N; i++) array[i] = i + 1;

        for (int count = 0; count < M; count++) {
            linstStrings = br.readLine().split(" ");

            int i = Integer.parseInt(linstStrings[0]) - 1;
            int j = Integer.parseInt(linstStrings[1]) - 1;

            if (i == j) continue;

            temp_value = array[i];
            array[i] = array[j];
            array[j] = temp_value;
        }

        for (int element : array) {
            System.out.print(element + " ");
        }
    }
}