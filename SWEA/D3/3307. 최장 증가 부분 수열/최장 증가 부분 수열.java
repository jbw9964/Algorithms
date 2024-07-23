import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        int testCount = 0;

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] inputs = br.readLine().split(" ");

            int[] arr = new int[N];
            for (int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(inputs[i]);

            int[] DP = new int[N];

            Arrays.fill(DP, 1);
            for (int pivot = 0; pivot < N; pivot++)
                for (int lower = 0; lower < pivot; lower++)
                    if (arr[lower] < arr[pivot])
                        DP[pivot] = Math.max(DP[pivot], DP[lower] + 1);

            int maxima = Arrays.stream(DP).max().getAsInt();

            answer.append("#").append(++testCount).append(" ");
            answer.append(maxima).append("\n");
        }

        System.out.println(answer.toString());
    }
}