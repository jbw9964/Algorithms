import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int K = Integer.parseInt(input[1]);

        ArrayDeque<Integer> deque = new ArrayDeque<>(N);
        for (int i = K + 1; i <= N; i++)    deque.addLast(i);
        for (int i = 1; i < K; i++)         deque.addLast(i);
        sb.append("<" + K);

        if (!deque.isEmpty()) {
            for (int i = 1; i < N - 1; i++) {
                for (int j = 0; j < K - 1; j++) deque.addLast(deque.poll());
                sb.append(", " + deque.poll());
            }
    
            sb.append(", " + deque.getFirst());
        }

        sb.append(">\n");
        System.out.println(sb.toString());
    }
}
