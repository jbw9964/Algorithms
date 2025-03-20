import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int[] numbers = IntStream.range(0, N).map(Main::parse).sorted().toArray();

        int[] possibleAdditions = new int[N * N];

        int cnt = 0;
        for (int num1 : numbers)    {
            for (int num2 : numbers)    {
                possibleAdditions[cnt++] = num1 + num2;
            }
        }
        Arrays.sort(possibleAdditions);

        for (int i = N - 1; i >= 0; i--) {
            int bigger = numbers[i];

            for (int j = 0; j <= i; j++) {
                int smaller = numbers[j];

                if (Arrays.binarySearch(possibleAdditions, 0, cnt, bigger - smaller) >= 0)  {
                    System.out.println(bigger);
                    return;
                }
            }
        }

        throw new RuntimeException();
    }

    private static int parse(int dummy) {
        try {
            return Integer.parseInt(br.readLine());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
