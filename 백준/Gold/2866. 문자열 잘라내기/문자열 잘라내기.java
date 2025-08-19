import java.io.*;
import java.util.*;
import java.util.stream.*;

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

            StringBuilder sb = new StringBuilder();
            for (int row = 0; row < R; row++) {
                sb.append(chars[row][col]);
            }

            tokens[col] = sb.toString();
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int left = 0, right = R - 1;
        int answer = left;

        while (left <= right)    {
            
            int mid = (left + right) >>> 1;

            if (isSafe(mid))    {
                answer = mid;
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        System.out.println(answer);
    }
    
    private static boolean isSafe(int beginIndex)   {
        
        Set<String> set = new HashSet<>();

        for (int col = 0; col < C; col++) {
            if (!set.add(getSubTokenFrom(beginIndex, col))) {
                return false;
            }
        }

        return true;
    }

    private static String getSubTokenFrom(int beginIndex, int col)    {
        return tokens[col].substring(beginIndex);
    }
}
