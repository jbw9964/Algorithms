import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        String[] inputString = br.readLine().split(" ");
        
        int num1 = Integer.parseInt(inputString[0]);
        int num2 = Integer.parseInt(inputString[1]);

        int gcd = GCD(num1, num2);
        int scm = gcd * (num1 / gcd) * (num2 / gcd);

        System.out.println(gcd + " " + scm);
    }

    public static int GCD(int a, int b) {
        if (a < b) {
            a += b;
            b = a - b;
            a = a - b;
        }

        int mod = 1;
        while (mod != 0) {
            mod = a % b;
            a = b;
            b = mod;
        }
        
        return a;
    }
}