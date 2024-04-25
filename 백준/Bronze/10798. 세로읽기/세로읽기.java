import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException {
        char[][] array = new char[5][15];
        
        for (int i = 0; i < 5; i++) {
            char[] input = br.readLine().toCharArray();
            for (int j = 0; j < input.length; j++)
            array[i][j] = input[j];
        }

        for (int i = 0; i < 15; i++)    {
            for (int j = 0; j < 5; j++) {
                if (array[j][i] == 0)   continue;
                sb.append(array[j][i]);
            }
        }

        System.out.print(sb.toString());
    }
}
