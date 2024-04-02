import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        HashMap<String, Integer> hashTableForName = new HashMap<>(N, 1);
        HashMap<Integer, String> hashTableForOrder = new HashMap<>(N, 1);

        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            hashTableForName.put(input, i);
            hashTableForOrder.put(i, input);
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();

            if ('0' <= input.charAt(0) && input.charAt(0) <= '9')
            sb.append(hashTableForOrder.get(Integer.parseInt(input)) + "\n");

            else
            sb.append(hashTableForName.get(input) + "\n");
        }

        System.out.println(sb.toString());
    }
}
