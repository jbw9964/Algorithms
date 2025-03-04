import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] parents;
    private static long[] values;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        parents = IntStream.range(0, N).toArray();
        values = new long[N];

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // : a / b = p / q
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long p = Integer.parseInt(st.nextToken());
            long q = Integer.parseInt(st.nextToken());
            //long gcd = gcd(p, q);
            //p /= gcd;
            //q /= gcd;

            if (values[a] == values[b] && values[a] == 0) {
                values[a] = p;
                values[b] = q;
            }

            else if (values[a] == 0) {
                values[a] = p * values[b];
                multiplyGivenToGroup(b, q);
            }

            else if (values[b] == 0) {
                values[b] = q * values[a];
                multiplyGivenToGroup(a, p);
            }

            else {
                long lcm = lcm(values[a], values[b]);
                long mulA = lcm / values[a];
                long mulB = lcm / values[b];

                // set values[a] & values[b] equal for convenience
                multiplyGivenToGroup(a, mulA);
                multiplyGivenToGroup(b, mulB);

                // adjust ratios
                multiplyGivenToGroup(a, p);
                multiplyGivenToGroup(b, q);
            }

            int p1 = findParent(a);
            int p2 = findParent(b);
            parents[Math.max(p1, p2)] = Math.min(p1, p2);
        }

        long gcd = Arrays.stream(values).reduce(values[0], Main::gcd);
        for (long val : values) {
            System.out.print(val / gcd + " ");
        }
        System.out.println();
    }

    private static void multiplyGivenToGroup(int indexInGroup, long mul) {
        if (mul > 1) {
            int parent = findParent(indexInGroup);
            IntStream.range(0, N)
                    .filter(i -> findParent(i) == parent)
                    .forEach(i -> values[i] *= mul);
        }
    }

    private static int findParent(int i) {
        return parents[i] = i == parents[i] ?
                parents[i] : findParent(parents[i]);
    }

    private static long lcm(long a, long b)    {
        long gcd = gcd(a, b);
        return (a / gcd) * (b / gcd) * gcd;
    }

    private static long gcd(long a, long b) {
        long max = Math.max(a, b);
        long min = Math.min(a, b);

        long mod;
        while ((mod = max % min) != 0)  {
            max = min;
            min = mod;
        }

        return min;
    }
}
