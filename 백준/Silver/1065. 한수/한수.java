import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (isValid(i)) {
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    private static boolean isValid(int num) {
        if (num < 100)  {
            return true;
        }

        char[] digits = String.valueOf(num).toCharArray();
        int diff = digits[1] - digits[0];

        for (int i = 1; i < digits.length; i++) {
            if (digits[i] - digits[i - 1] != diff) {
                return false;
            }
        }

        return true;
    }
}
