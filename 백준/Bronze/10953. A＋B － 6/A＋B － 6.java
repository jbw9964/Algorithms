import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        
        String[] inpuStrings;
        int num1, num2;

        for (int i = 1; i <= N; i++) {
            inpuStrings = br.readLine().split(",");
            num1 = Integer.parseInt(inpuStrings[0]);
            num2 = Integer.parseInt(inpuStrings[1]);

            System.out.println(num1 + num2);
        }
    }
}
