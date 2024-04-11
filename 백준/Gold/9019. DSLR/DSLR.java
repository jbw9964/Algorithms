import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final int MOD = 10_000;

    public static void main (String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            solve(
                Integer.parseInt(tokenizer.nextToken()), 
                Integer.parseInt(tokenizer.nextToken())
            );
        }

    }

    public static void solve(int start, int target)  {
        int[] recordTable = new int[MOD];
        Arrays.fill(recordTable, -1);

        recordTable[start] = start;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        LOOP_FLAG : 
        while (true)    {
            int current = queue.poll();

            int[] newNumbers = {D(current), S(current), L(current), R(current)};
            for (int num : newNumbers)  {

                if (recordTable[num] != -1)     continue;

                recordTable[num] = current;
                queue.add(num);

                if (num == target)  break LOOP_FLAG;
            }
        }

        queue.clear();

        StringBuilder sb = new StringBuilder();

        do {
            int prevNum = recordTable[target];

            if (D(prevNum) == target)       sb.append("D");
            else if (S(prevNum) == target)  sb.append("S");
            else if (L(prevNum) == target)  sb.append("L");
            else                            sb.append("R");

            target = prevNum;
        } while (target != start);

        System.out.println(sb.reverse().toString());
    }

    public static int D(int num)    {return 2 * num % MOD;}
    public static int S(int num)    {return num == 0 ? MOD - 1 : num - 1;}
    public static int L(int num)    {
        if (10 * num < MOD)     return 10 * num;
        int quotient = num / 1_000;
        int modulo = num - (1_000 * quotient);
        return 10 * modulo + quotient;
    }
    public static int R(int num)    {
        int quotient = num / 10;
        int modulo = num - 10 * quotient;
        return 1_000 * modulo + quotient;
    }
}
