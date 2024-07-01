
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.io.IOException;

class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

	public static void main(String[] args) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        Integer[] array = new Integer[N];
        tokenizer = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++)
        array[i] = Integer.parseInt(tokenizer.nextToken());

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int count1 = Integer.bitCount(o1);
                int count2 = Integer.bitCount(o2);

                return count1 != count2 ? count2 - count1 : o2 - o1;
            }
        });

        System.out.println(array[K - 1]);
	}
}