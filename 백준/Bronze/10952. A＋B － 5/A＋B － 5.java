import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        String[] inpuStrings;
        int num1, num2;

        while (true) {
            inpuStrings = br.readLine().split(" ");
            num1 = Integer.parseInt(inpuStrings[0]);
            num2 = Integer.parseInt(inpuStrings[1]);

            if (num1 == 0 && num2 == 0) break;
            
            System.out.println(num1 + num2);
        }
    }
}
