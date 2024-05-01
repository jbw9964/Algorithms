import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int r1 = Integer.parseInt(tokenizer.nextToken());
        int c1 = Integer.parseInt(tokenizer.nextToken());
        int r2 = Integer.parseInt(tokenizer.nextToken());
        int c2 = Integer.parseInt(tokenizer.nextToken());

        int maxLength = 0;
        Queue<Integer> queue = new LinkedList<>();

        for (int i = r1; i <= r2; i++)  {
            for (int j = c1; j <= c2; j++)  {
                int pivot = getPivot(i, j);
                int pivotR = (int) (Math.sqrt(pivot) / 2);
                int pivotC;
                int value = 0;

                if (-i < j)     pivotC = pivotR + 1;
                else            {
                    pivotR *= -1;
                    pivotC = pivotR;
                }

                value = pivot + getDist(pivotR, pivotC, i, j);
                int length = String.valueOf(value).length();

                if (maxLength < length) maxLength = length;

                queue.add(value);
            }
            queue.add(0);
        }
        
        for (int value : queue) {
            if (value == 0)     {
                sb.append("\n");
                continue;
            }

            String number = String.valueOf(value);
            int count = 0;
            while (count++ < maxLength - number.length())
            sb.append(" ");
            sb.append(number).append(" ");
        }

        System.out.print(sb.toString());
    }

    private static int getPivot(int r, int c)   {
        int pivot = 0;

        int y = -c;
        int x = r;

        if (y >= x)  {
            int minima = Math.min(r, c);
            pivot = (2 * minima) * (2 * minima) + 1;
        }

        else    {
            int value;
            if (r < c)  value = 2 * c - 1;
            else        value = 2 * r + 1;
            pivot = value * value + 1;
        }

        return pivot;
    }

    private static int getDist(int pivotR, int pivotC, int r, int c)    {
        int dist = 0;

        int y = -c;
        int x = r;

        if (y >= x) {
            if (pivotR == r)        dist = pivotC - c;
            else if (pivotC == c)   dist = r - pivotR;
        }

        else    {
            if (pivotR == r)        dist = c - pivotC;
            else if (pivotC == c)   dist = pivotR - r;
        }

        return dist;
    }
}
