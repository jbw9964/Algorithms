import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[][] numbers;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        numbers = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            numbers[i][0] = a;
            numbers[i][1] = b;
            numbers[i][2] = c;
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];
        maxDp[0][0] = minDp[0][0] = numbers[0][0];
        maxDp[0][1] = minDp[0][1] = numbers[0][1];
        maxDp[0][2] = minDp[0][2] = numbers[0][2];

        for (int n = 1; n < N; n++) {

            int a = numbers[n][0];
            int b = numbers[n][1];
            int c = numbers[n][2];

            int[] maxPrev, maxCurr;
            int[] minPrev, minCurr;

            if (n % 2 != 0) {
                maxPrev = maxDp[0];
                minPrev = minDp[0];

                maxCurr = maxDp[1];
                minCurr = minDp[1];
            }
            else {
                maxPrev = maxDp[1];
                minPrev = minDp[1];

                maxCurr = maxDp[0];
                minCurr = minDp[0];
            }

            maxCurr[0] = a + max(maxPrev[0], maxPrev[1]);
            maxCurr[1] = b + max(maxPrev[0], maxPrev[1], maxPrev[2]);
            maxCurr[2] = c + max(maxPrev[1], maxPrev[2]);

            minCurr[0] = a + min(minPrev[0], minPrev[1]);
            minCurr[1] = b + min(minPrev[0], minPrev[1], minPrev[2]);
            minCurr[2] = c + min(minPrev[1], minPrev[2]);
        }

        int i = N % 2 != 0 ? 0 : 1;

        int max = max(maxDp[i][0], maxDp[i][1], maxDp[i][2]);
        int min = min(minDp[i][0], minDp[i][1], minDp[i][2]);

        System.out.printf("%d %d\n", max, min);
    }

    private static int max(int... nums) {

        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int max = nums[0];
        for (int num : nums) {
            max = Math.max(max, num);
        }
        return max;
    }

    private static int min(int... nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException();
        }

        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        return min;
    }
}
