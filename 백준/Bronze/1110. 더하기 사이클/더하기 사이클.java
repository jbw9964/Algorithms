import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        int ans = 1;
        int converted = convert(N);

        while (converted != N)  {
            converted = convert(converted);
            ans++;
        }

        System.out.println(ans);
    }

    private static int convert(int n)   {
        if (0 <= n && n <= 9)   {
            return 10 * n + n;
        }

        if (n >= 100 || n < 0)   {
            throw new IllegalArgumentException();
        }

        int sum = n / 10 + n % 10;
        return (n % 10) * 10 + sum % 10;
    }
}
