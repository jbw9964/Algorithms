import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int[] Result = {0, 0, 0};

    public static void main(String[] args) throws IOException {
        
        int element;
        String[] stringArray;

        for (int i = 0; i < 9; i++) {
            stringArray = br.readLine().split(" ");

            for (int j = 0; j < 9; j++) {
                element = Integer.parseInt(stringArray[j]);
                if (element >= Result[0]) {
                    Result[0] = element;
                    Result[1] = i + 1;
                    Result[2] = j + 1;
                }
            }
        }

        System.out.println(Result[0] + "\n" + Result[1] + " " + Result[2]);
    }
}