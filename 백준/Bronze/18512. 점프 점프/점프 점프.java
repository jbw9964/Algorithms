import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int X, P1;
    private static int Y, P2;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        P1 = Integer.parseInt(st.nextToken());
        P2 = Integer.parseInt(st.nextToken());

    }

    public static void main(String[] args) throws IOException {

        init();

        /*
        등차수열
        AP_n = P1 + X * n
        AP_m = P2 + Y * m

        [AP1 = AP2]
        Xn = Ym + P2 - P1

        X, Y, P1, P2 는 자연수이며, 미지수 n, m 또한 자연수여야 하므로
        n, m 해가 존재할 필요 충분 조건은 (Ym + P2 - P1) % X == 0 를 만족하는 정수 m 이 존재하는 것이다.

        X, Y 의 최소 공배수를 Z 라 칭하면
        정수 m 으로 인해 계산되는 (Ym) % X 은 오직 [Z / Y] 개이다.

        때문에 m = 1, ..., Z/Y 까지
        (Ym + P2 - P1) % X == 0 를 만족하는 경우가 존재한다면
        해가 존재한다.

        만일 어느 자연수 m 이 위 식을 만족한다 해도
        문제 특성상 m 에 상하는 등차수열 AP_n, AP_m 의 값이 모두 P1, P2 보다 크거나 같아야 한다.

        때문에 (Ym + P2 - P1) % X == 0 를 만족하는 m 을 발견했을 시,
        m += Z/Y 하며 P1, P2 보다 크거나 같은 값을 구성하는 m 을 찾는다.
         */

        int gcd = gcd(X, Y);
        int lcm = (X * Y) / gcd;

        // EQ1 : Ym + P2 - P1
        // EQ2 : P2 + Y * m

        for (int m = 0; m < lcm / Y; m++) {
            int eq1 = EQ1(m);

            if (eq1 % X == 0)   {

                int eq2;

                while ((eq2 = EQ2(m)) < P1 || eq2 < P2)   {
                    m += lcm / Y;
                }

                System.out.println(eq2);
                return;
            }
        }

        System.out.println(-1);
    }

    private static int EQ1(int m)   {
        return Y * m + P2 - P1;
    }

    private static int EQ2(int m)   {
        return P2 + Y * m;
    }

    private static int gcd(int a, int b)    {
        if (a < b)  {
            return gcd(b, a);
        }

        int mod;
        while ((mod = a % b) != 0) {
            a = b;
            b = mod;
        }

        return b;
    }
}
