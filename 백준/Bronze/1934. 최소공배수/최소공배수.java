
import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int b = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());

            int gcd = getGCD(a, b);

            int mulA = a / gcd;
            int mulB = b / gcd;

            sb.append(gcd * mulA * mulB).append("\n");
        }

        System.out.println(sb.toString());
    }

    // a >= b
    public static int getGCD(int a, int b) {

        while (a % b != 0)  {
            int tmp = a % b;
            a = b;
            b = tmp;
        }

        return b;
    }
}

