import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static String[] words;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        Comparator<String> answerComparator = (s1, s2) -> {
            int len1 = s1.length();
            int len2 = s2.length();

            return len1 != len2 ? Integer.compare(len1, len2) : s1.compareTo(s2);
        };

        Arrays.sort(words, answerComparator);

        StringBuilder answer = new StringBuilder()
                .append(words[0])
                .append('\n');

        for (int i = 1; i < N; i++) {
            if (!words[i].equals(words[i - 1])) {
                answer.append(words[i]).append('\n');
            }
        }

        System.out.print(answer.toString());
    }
}
