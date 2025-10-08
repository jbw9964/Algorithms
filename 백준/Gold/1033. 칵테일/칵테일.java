import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static List<Ratio> ratioList;
    private static List<Integer>[] neighbours;
    private static long[] answerRatio;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        ratioList = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long p = Long.parseLong(st.nextToken());
            long q = Long.parseLong(st.nextToken());

            long gcd = gcd(p, q);
            p /= gcd;
            q /= gcd;

            ratioList.add(new Ratio(a, b, p, q));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        answerRatio = new long[N];
        Arrays.fill(answerRatio, 1L);

        //noinspection unchecked
        neighbours = IntStream.range(0, N)
                .mapToObj(i -> new ArrayList<>())
                .toArray(List[]::new);

        boolean[] visited = new boolean[N];

        for (Ratio ratio : ratioList) {

            int a = ratio.a, b = ratio.b;
            long p = ratio.p, q = ratio.q;

            long lcm = lcm(answerRatio[a], answerRatio[b]);
            long targetA = p * lcm;
            long mulA = targetA / gcd(targetA, answerRatio[a]);

            long targetB = q * lcm;
            long mulB = targetB / gcd(targetB, answerRatio[b]);

            propagateRatioFrom(a, mulA, visited);
            propagateRatioFrom(b, mulB, visited);

            neighbours[a].add(b);
            neighbours[b].add(a);

            Arrays.fill(visited, false);
        }

        long gcd = Arrays.stream(answerRatio)
                .reduce(Main::gcd)
                .orElseThrow(RuntimeException::new);

        String answer = Arrays.stream(answerRatio)
                .map(r -> r / gcd)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(answer);
    }

    private static void propagateRatioFrom(
            int from, long multiplier, boolean[] visited
    ) {

        if (multiplier == 1L) {
            return;
        }

        answerRatio[from] *= multiplier;
        visited[from] = true;

        for (int neighbor : neighbours[from]) {
            if (!visited[neighbor]) {
                propagateRatioFrom(neighbor, multiplier, visited);
            }
        }
    }

    private static long gcd(long a, long b) {

        if (a < b) {
            return gcd(b, a);
        }

        long mod;
        while ((mod = a % b) != 0) {
            a = b;
            b = mod;
        }

        return b;
    }

    private static long lcm(long a, long b) {
        return (a * b) / gcd(a, b);
    }
}

class Ratio {

    final int a, b;
    final long p, q;

    public Ratio(int a, int b, long p, long q) {
        this.a = a;
        this.b = b;
        this.p = p;
        this.q = q;
    }

    @Override
    public String toString() {
        return "Ratio{" +
               "a=" + a +
               ", b=" + b +
               ", p=" + p +
               ", q=" + q +
               '}';
    }
}
