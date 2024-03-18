import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        String lineString;

        int[][] arrayCount = new int[T][26];

        for (int lineCount = 0; lineCount < T; lineCount++) {
            lineString = br.readLine().replaceAll(" ", "");

            for (int i = 0; i < lineString.length(); i++) {
                arrayCount[lineCount][lineString.charAt(i) - 'a'] += 1;
            }
        }

        for (int[] array : arrayCount) {
            int max = -1;
            int max_index = -1;

            for (int i = 0; i < array.length; i++) {
                if (array[i] >= max) {
                    max = array[i];
                    max_index = i;
                }
            }

            boolean flag = true;
            for (int i = 0; i < max_index; i++) {
                if (array[i] == max) {
                    flag = false;
                    break;
                }
            }

            System.out.println(
                flag ? String.valueOf((char) (max_index + 'a')) : "?"
            );
        }
    }
}