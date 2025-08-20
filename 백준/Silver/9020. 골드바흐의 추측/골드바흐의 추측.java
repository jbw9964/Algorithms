import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int T;
    private static int[] numbers;
    private static boolean[] nonPrimRecord;

    private static void init() throws IOException {

        T = Integer.parseInt(br.readLine());
        numbers = new int[T];
        for (int i = 0; i < T; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        nonPrimRecord = new boolean[10_000 + 1];
        nonPrimRecord[0] = nonPrimRecord[1] = true;

        double sqrt = Math.sqrt(nonPrimRecord.length);
        for (int num = 2; num <= sqrt; num++) {

            if (nonPrimRecord[num]) {
                continue;
            }

            for (int mul = 2 * num; mul < nonPrimRecord.length; mul += num) {
                nonPrimRecord[mul] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        StringBuilder answer = new StringBuilder();

        LOOP:
        for (int number : numbers) {

            for (int n1 = number / 2; n1 >= 2; n1--) {

                if (!isPrime(n1))   {
                    continue;
                }

                int n2 = number - n1;

                if (isPrime(n2)) {
                    answer.append(n1).append(" ").append(n2).append("\n");
                    continue LOOP;
                }
            }

            throw new RuntimeException();
        }

        System.out.println(answer.toString());
    }

    private static boolean isPrime(int n) {
        return !nonPrimRecord[n];
    }
}
