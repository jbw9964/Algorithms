import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int len = 0;
        int[] arr = new int[N];

        for (int curr : numbers) {
            int index = Arrays.binarySearch(arr, 0, len, curr);
            int insertionIndex = index < 0 ? -1 * (index + 1) : index;

            if (insertionIndex >= len)  {
                len++;
            }
            arr[insertionIndex] = curr;
        }

        System.out.println(len);
    }
}
