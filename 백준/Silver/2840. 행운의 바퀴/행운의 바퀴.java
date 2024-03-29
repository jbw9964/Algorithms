import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        String[] roulette = new String[N];
        boolean[] visitTable = new boolean[N];
        int cursor = 0;

        boolean flag = true;
        for (int i = 0; i < K && flag; i++) {
            tokenizer = new StringTokenizer(br.readLine());

            int movement = Integer.parseInt(tokenizer.nextToken());
            String letter = tokenizer.nextToken();

            cursor = (cursor + movement) % N;

            if (!visitTable[cursor])    {       // encountered unknown space
                for (int j = 0; j < N; j++) if (letter.equals(roulette[j])) {   // check whether letter duplicates
                    flag = false;
                    break;
                }

                visitTable[cursor] = true;
                roulette[cursor] = letter;
            }

            else if (!letter.equals(roulette[cursor]))   {  // encountered known space & check whether letter stay same
                flag = false;
                break;
            }
        }

        if (!flag)      sb.append("!");
        else    {
            for (int i = cursor; i >= 0; i--) {
                if (visitTable[i])  sb.append(roulette[i]);
                else                sb.append("?");
            }
            for (int i = N - 1; i > cursor; i--) {
                if (visitTable[i])  sb.append(roulette[i]);
                else                sb.append("?");
            }
        }

        System.out.println(sb.toString());
    }
}
