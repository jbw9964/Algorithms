import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );
    
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = N - 1; i >= 0; i--) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int len = 0;
        for (int num : arr) {

            int index = Arrays.binarySearch(dp, 0, len, num);
            int insertionPoint = index < 0 ? -index - 1 : index;

            if (insertionPoint >= len)  {
                len++;
            }

            dp[insertionPoint] = num;
        }

        System.out.println(N - len);
    }
}
