import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );
    private static BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );
    private static String lineString = null;

    private static int row_size, col_size;
    private static int[][] arrayA = null;
    private static int[][] arrayB = null;

    public static void main(String[] args) throws IOException {
        
        Main.readInput();

        for (int i = 0; i < row_size; i++) {
            for (int j = 0; j < col_size; j++) {
                bw.write(arrayA[i][j] + arrayB[i][j] + " ");
            };  bw.newLine();
        }

        bw.flush();
    }

    public static void readInput() throws IOException {
        lineString = br.readLine();
        
        StringTokenizer temp_Tokenizer = new StringTokenizer(lineString);
        row_size = Integer.parseInt(temp_Tokenizer.nextToken());
        col_size = Integer.parseInt(temp_Tokenizer.nextToken());
        
        arrayA = new int[row_size][col_size];
        arrayB = new int[row_size][col_size];
        
        for (int i = 0; i < row_size; i++) {
            lineString = br.readLine();
            String[] rowString = lineString.split(" ");

            for (int j = 0; j < col_size; j++) {
                arrayA[i][j] = Integer.parseInt(rowString[j]);
            }
        }

        for (int i = 0; i < row_size; i++) {
            lineString = br.readLine();
            String[] rowString = lineString.split(" ");

            for (int j = 0; j < col_size; j++) {
                arrayB[i][j] = Integer.parseInt(rowString[j]);
            }
        }
    }
}
