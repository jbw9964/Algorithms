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

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = K + 1; i <= N; i++)    deque.addLast(i);
        for (int i = 1; i < K; i++)         deque.addLast(i);
        sb.append("<" + K);

        int count = 1;
        while (!deque.isEmpty())    {
            if (count++ % K != 0)   deque.addLast(deque.poll());
            else                    sb.append(", " + deque.poll());
        }
        
        sb.append(">\n");

        System.out.println(sb.toString());
    }
}
