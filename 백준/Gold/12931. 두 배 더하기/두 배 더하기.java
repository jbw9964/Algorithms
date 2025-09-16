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

        int maxMulCount, addCount;
        maxMulCount = addCount = 0;

        for (int val : numbers) {

            int mulCount = 0;

            while (val != 0)    {

                if ((val & 1) == 0) {
                    val >>>= 1;
                    mulCount++;
                }
                else {
                    val--;
                    addCount++;
                }
            }

            maxMulCount = Math.max(maxMulCount, mulCount);
        }

        System.out.println(addCount + maxMulCount);
    }
}
