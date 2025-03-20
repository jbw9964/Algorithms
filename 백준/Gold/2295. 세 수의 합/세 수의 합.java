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

        TreeSet<Integer> possibleAdditions = new TreeSet<>();
        for (int num1 : numbers)    {
            for (int num2 : numbers)    {
                possibleAdditions.add(num1 + num2);
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            int bigger = numbers[i];

            for (int j = 0; j <= i; j++) {
                int smaller = numbers[j];

                if (possibleAdditions.contains(bigger - smaller))   {
                    System.out.println(bigger);
                    return;
                }
            }
        }

        throw new RuntimeException();
    }

    private static int parse(int dummy)  {
        try {
            return Integer.parseInt(br.readLine());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
