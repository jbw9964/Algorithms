
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[] parents;

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        parents = IntStream.range(0, N).toArray();

        int cnt = 0;
        boolean flag = false;
        while (cnt++ < M)   {
            st = new StringTokenizer(br.readLine());

            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            int parent1 = getRootParent(v1);
            int parent2 = getRootParent(v2);

            if (parent1 == parent2) {
                flag = true;
                break;
            }

            int root = Math.min(parent1, parent2);
            int child = Math.max(parent1, parent2);

            parents[child] = root;
        }

        System.out.println(flag ? cnt : 0);
    }

    private static int getRootParent(int v) {
        return v == parents[v] ?
                v : (parents[v] = getRootParent(parents[v]));
    }
}
