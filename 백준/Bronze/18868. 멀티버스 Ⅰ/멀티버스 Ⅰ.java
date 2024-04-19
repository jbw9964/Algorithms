import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    
    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        
        String[] inputString = br.readLine().split(" ");

        int M = Integer.parseInt(inputString[0]);
        int N = Integer.parseInt(inputString[1]);

        int[][] array = new int[M][N];
        for (int[] row : array) {
            inputString = br.readLine().split(" ");
            for (int i = 0; i < inputString.length; i++)
            row[i] = Integer.parseInt(inputString[i]);
        }

        int count = 0;

        for (int r1 = 0; r1 < M - 1; r1++)  {
            int[] row1 = array[r1];

            for (int r2 = r1 + 1; r2 < M; r2++)  {
                int[] row2 = array[r2];

                boolean check = true;

                LOOP_FLAG:
                for (int i = 0; i < N - 1; i++) {
                    for (int j = i + 1; j < N; j++) {

                        if (row1[i] == row1[j] && row2[i] == row2[j])   continue;
                        if (row1[i] < row1[j] && row2[i] < row2[j])     continue;
                        if (row1[i] > row1[j] && row2[i] > row2[j])     continue;
                        
                        check = false;
                        break LOOP_FLAG;
                    }
                }

                if (check)  count++;
            }
        }

        System.out.println(count);
    }
}
