import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
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
        HashSet<Integer> hashSet = new HashSet<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            array[i] = num;
            hashSet.add(num);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            int num = array[i];

            if (hashSet.contains(target - num)) count++;
        }

        System.out.println(count / 2);
    }
}
