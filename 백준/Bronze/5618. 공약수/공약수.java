import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] numbers;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

    }

    public static void main(String[] args) throws IOException {

        init();

        int gcd = gcd(numbers);

        double sqrt = Math.sqrt(gcd);

        List<Integer> divisorList = new ArrayList<>();
        for (int trial = 1; trial <= sqrt; trial++) {

            if (gcd % trial != 0) {
                continue;
            }

            divisorList.add(trial);
            if (trial != gcd / trial) {
                divisorList.add(gcd / trial);
            }
        }

        divisorList.sort(Comparator.naturalOrder());

        StringBuilder answer = new StringBuilder();
        for (int divisor : divisorList) {
            answer.append(divisor).append("\n");
        }

        System.out.print(answer.toString());
    }

    private static int gcd(int... nums) {

        int len = nums.length;

        if (len == 0) {
            throw new IllegalArgumentException();
        }

        int gcd = nums[0];
        for (int i = 1; i < len; i++) {
            gcd = gcd(gcd, nums[i]);
        }

        return gcd;
    }

    private static int gcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        int mod;

        while ((mod = a % b) != 0) {
            a = b;
            b = mod;
        }

        return b;
    }
}
