import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        StringBuilder first = new StringBuilder().append(br.readLine());
        StringBuilder second = new StringBuilder().append(br.readLine());

        int lcm = getLCM(first.length(), second.length());

        int repeat1 = lcm / first.length();
        int repeat2 = lcm / second.length();

        String firstStr = first.toString();
        String secondStr = second.toString();

        for (int i = 1; i < repeat1; i++) {
            first.append(firstStr);
        }
        for (int i = 1; i < repeat2; i++) {
            second.append(secondStr);
        }

        firstStr = first.toString();
        secondStr = second.toString();

        System.out.println(
                firstStr.equals(secondStr) ? 1 : 0
        );
    }

    private static int getLCM(int a, int b) {

        int max = Math.max(a, b);
        int min = Math.min(a, b);

        int gcd = getGCD(max, min);
        a /= gcd;
        b /= gcd;

        return gcd * a * b;
    }

    private static int getGCD(int a, int b) {
        while (a % b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }

        return b;
    }
}
