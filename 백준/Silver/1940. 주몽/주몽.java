import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int[] array = new int[N];
        TreeSet<Integer> treeSet = new TreeSet<>();

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(tokenizer.nextToken());
            array[i] = num;
            treeSet.add(num);
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            int num = array[i];

            if (treeSet.contains(target - num)) count++;
        }

        System.out.println(count / 2);
    }
}
