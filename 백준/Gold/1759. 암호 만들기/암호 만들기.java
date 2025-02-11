import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int L, C;
    private static String[] letters;

    private static final StringBuilder sb = new StringBuilder();

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        letters = br.readLine().split(" ");
        Arrays.sort(letters);
    }

    public static void main(String[] args) throws IOException {

        init();

        dfs(0, new ArrayList<>(L));

        System.out.print(sb.toString());
    }

    private static void dfs(int index, List<String> tmp) {

        int size = tmp.size();

        if (size == L) {
            int AEIOU = countAEIOU(tmp);
            int others = size - AEIOU;

            if (AEIOU >= 1 && others >= 2) {
                for (String letter : tmp)   {
                    sb.append(letter);
                }

                sb.append("\n");
            }

            return;
        }

        else if (index == C) {
            return;
        }

        tmp.add(letters[index]);
        dfs(index + 1, tmp);
        tmp.remove(tmp.size() - 1);

        dfs(index + 1, tmp);
    }

    private static int countAEIOU(List<String> letters) {
        int cnt = 0;

        for (String s : letters) {
            if ("a".equals(s) || "e".equals(s) || "i".equals(s)
                    || "o".equals(s) || "u".equals(s)) {
                cnt++;
            }
        }

        return cnt;
    }
}