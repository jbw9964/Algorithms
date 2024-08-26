import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        char[] table = br.readLine()
                         .toCharArray();
        int[] costs = new int[N];

        for (int i = 0; i < N - 1; i++) {
            char origin = table[i];
            int costOrigin = costs[i];

            if (costOrigin == 0 && i != 0)
                continue;

            char target = origin == 'B' ? 'O' : origin == 'O' ? 'J' : 'B';

            for (int j = i + 1; j < N; j++) {
                if (table[j] != target)
                    continue;

                int costAlternative = costOrigin + (j - i) * (j - i);

                if (costs[j] == 0)
                    costs[j] = costAlternative;
                else
                    costs[j] = Math.min(costs[j], costAlternative);
            }
        }

        System.out.println(costs[N - 1] == 0 ? -1 : costs[N - 1]);
    }
}
