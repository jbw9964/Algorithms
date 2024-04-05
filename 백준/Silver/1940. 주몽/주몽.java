import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int[] array = new int[N];
        for (int i = 0; i < N; i++)
        array[i] = Integer.parseInt(tokenizer.nextToken());

        Arrays.sort(array);

        int pt1 = 0;
        int pt2 = N - 1;

        int count = 0;
        while (pt1 < pt2) {
            int sum = array[pt1] + array[pt2];

            if (array[pt1] >= target)   break;

            if (sum < target)       pt1++;
            else if (sum > target)  pt2--;
            else {
                pt1++;
                count++;
            }
        }

        System.out.println(count);
    }
}
