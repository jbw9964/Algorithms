import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);
        int M = Integer.parseInt(input[1]);

        int[] targets = new int[M];
        input = br.readLine().split(" ");
        for (int i = 0; i < input.length; i++)  targets[i] = Integer.parseInt(input[i]);
        
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 1; i <= N; i++)    deque.add(i);


        int result = 0;
        for (int target : targets) {
            int count = 0;

            while (deque.peek() != target) {
                deque.add(deque.pollFirst());
                count++;
            }

            count = deque.size() - count > count ? count : deque.size() - count;
            result += count;
            deque.pollFirst();
        }

        System.out.println(result);
    }
}
