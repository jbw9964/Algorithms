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

        HashMap<String, Integer> hashMap = new HashMap<>(N, 1);
        String[] pokemonArray = new String[N];

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            hashMap.put(name, i);
            pokemonArray[i - 1] = name;
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();

            if ('0' <= input.charAt(0) && input.charAt(0) <= '9')
            sb.append(pokemonArray[Integer.parseInt(input) - 1] + "\n");

            else
            sb.append(hashMap.get(input) + "\n");
        }

        System.out.println(sb.toString());
    }
}
