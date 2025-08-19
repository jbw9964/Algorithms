import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C;
    private static String[] tokens;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] chars = new char[R][];
        for (int i = 0; i < R; i++) {
            chars[i] = br.readLine().toCharArray();
        }

        tokens = new String[C];
        for (int col = 0; col < C; col++) {
            tokens[col] = genColumnStr(col, chars);
        }
    }

    private static String genColumnStr(int col, char[][] chars) {

        StringBuilder sb = new StringBuilder();
        for (char[] row : chars) {
            sb.append(row[col]);
        }

        return sb.toString();
    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;

        Set<String> set = new HashSet<>();

        LOOP:
        while (true)    {
            for (int i = 0; i < tokens.length; i++) {
                String subToken = tokens[i].substring(1);

                if (set.contains(subToken)) {
                    break LOOP;
                }

                set.add(subToken);
                tokens[i] = subToken;
            }

            answer++;
            set.clear();
        }

        System.out.println(answer);
    }

}
