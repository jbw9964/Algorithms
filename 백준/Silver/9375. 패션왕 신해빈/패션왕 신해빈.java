import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < T; i++) {

            int N = Integer.parseInt(br.readLine());

            if (N == 0) {
                sb.append("0\n");
                continue;
            }

            for (int j = 0; j < N; j++) {
                StringTokenizer tokenizer = new StringTokenizer(br.readLine());

                String name = tokenizer.nextToken();
                String key = tokenizer.nextToken();

                hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
            }

            Set<String> keySet = hashMap.keySet();
            int combinations = 1;

            for (String key : keySet)
            combinations *= hashMap.get(key) + 1;

            sb.append(--combinations + "\n");
            hashMap.clear();
        }

        System.out.print(sb.toString());
    }
}
