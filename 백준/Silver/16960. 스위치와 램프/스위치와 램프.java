import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Integer>[] connection;
    private static int[] inDegree;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //noinspection unchecked
        connection = new List[N];
        inDegree = new int[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());
            connection[i] = new ArrayList<>(cnt);

            for (int j = 0; j < cnt; j++) {
                int lamp = Integer.parseInt(st.nextToken());
                inDegree[lamp]++;
                connection[i].add(lamp);
            }
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;

        LOOP:
        for (List<Integer> conn : connection)   {

            for (int lamp : conn) {
                if (inDegree[lamp] <= 1)    {
                    continue LOOP;
                }
            }

            answer = 1;
            break;
        }

        System.out.println(answer);
    }
}
