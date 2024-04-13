import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            if (N >= 33)    {sb.append("0\n"); br.readLine(); continue;}

            String[] inputStrings = br.readLine().split(" ");

            int minima = 4 * 3;
            int distance;
            for (int i = 0; i < N - 2; i++)
            for (int j = i + 1; j < N - 1; j++)
            for (int k = j + 1; k < N; k++)
            minima = minima > (distance = dist(
                inputStrings[i], inputStrings[j], inputStrings[k]
            )) ? distance : minima;
        
            sb.append(minima).append("\n");
        }

        System.out.print(sb.toString());
    }

    public static int dist(String st1, String st2, String st3)  {
        return dist(st1, st2) + dist(st2, st3) + dist(st3, st1);
    }
    public static int dist(String st1, String st2)  {
        int count = 0;
        char[] array1 = st1.toCharArray();
        char[] array2 = st2.toCharArray();

        for (int i = 0; i < array1.length; i++)
        if (array1[i] != array2[i]) count++;

        return count;
    }
}
