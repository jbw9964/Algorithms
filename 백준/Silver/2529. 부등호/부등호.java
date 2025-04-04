import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int K;
    private static char[] given;
    private static final boolean[] used = new boolean[10];

    private static String MINIMA, MAXIMA;

    private static void init() throws IOException {
        K = Integer.parseInt(br.readLine());
        given = br.readLine().replaceAll(" ", "").toCharArray();
    }

    public static void main(String[] args) throws IOException {
        init();

        for (int i = 0; i <= 9; i++) {
            searchFrom(i);
        }

        System.out.println(MAXIMA);
        System.out.println(MINIMA);
    }

    public static void searchFrom(int first)    {
        Arrays.fill(used, false);
        used[first] = true;

        backTracking(1, first, new StringBuilder().append(first));
    }

    public static void backTracking(int cnt, int prevVal, StringBuilder storage)  {

        if (cnt == K + 1) {

            String answer = storage.toString();
            long value = toLong(answer);

            if (MINIMA == null || toLong(MINIMA) > value) {
                MINIMA = answer;
            }
            if (MAXIMA == null || toLong(MAXIMA) < value) {
                MAXIMA = answer;
            }

            return;
        }

        char inequality = given[cnt - 1];
        if (inequality == '<')  {
            for (int candidate = prevVal + 1; candidate <= 9; candidate++) {
                if (!used[candidate]) {
                    used[candidate] = true;
                    storage.append(candidate);
                    backTracking(cnt + 1, candidate, storage);
                    storage.deleteCharAt(cnt);
                    used[candidate] = false;
                }
            }
        }
        else if (inequality == '>')  {
            for (int candidate = 0; candidate < prevVal; candidate++) {
                if (!used[candidate]) {
                    used[candidate] = true;
                    storage.append(candidate);
                    backTracking(cnt + 1, candidate, storage);
                    storage.deleteCharAt(cnt);
                    used[candidate] = false;
                }
            }
        }
    }

    private static long toLong(String str)    {
        return Long.parseLong(str);
    }
}
