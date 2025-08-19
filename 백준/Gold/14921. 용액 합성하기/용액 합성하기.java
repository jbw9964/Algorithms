import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] values;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        values = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = Integer.MAX_VALUE;

        for (int pivotIndex = 0; pivotIndex < N - 1; pivotIndex++) {

            int pivotValue = values[pivotIndex];

            int index = Arrays.binarySearch(values, pivotIndex + 1, N, -pivotValue);

            if (index >= 0) {
                answer = 0;
                break;
            }

            int insertionPoint = -(index + 1);

            if (
                    insertionPoint - 1 != pivotIndex &&
                    Math.abs(pivotValue + values[insertionPoint - 1]) < Math.abs(answer)
            )    {
                answer = pivotValue + values[insertionPoint - 1];
            }

            int closestValue = values[Math.min(insertionPoint, N - 1)];

            int answerValue = Math.abs(answer);
            int currentValue = Math.abs(pivotValue + closestValue);

            if (currentValue < answerValue) {
                answer = pivotValue + closestValue;
            }
        }

        System.out.println(answer);
    }
}
