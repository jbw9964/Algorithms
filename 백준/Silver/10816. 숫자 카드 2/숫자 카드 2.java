import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] cards, checks;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        cards = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        M = Integer.parseInt(br.readLine());
        checks = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        Map<Integer, Integer> map = new HashMap<>(2 * N);

        for (int card : cards) {
            int count = map.getOrDefault(card, 0);
            map.put(card, count + 1);
        }

        StringBuilder answer = new StringBuilder();
        for (int check : checks) {
            int count = map.getOrDefault(check, 0);
            answer.append(count).append(" ");
        }

        System.out.println(answer.toString());
    }
}
