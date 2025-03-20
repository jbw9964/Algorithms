import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] numOfParts, outDegree, inDegree;
    private static List<Info>[] infos;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        numOfParts = new int[N + 1];
        outDegree = new int[N + 1];
        inDegree = new int[N + 1];
        infos = new List[N + 1];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            outDegree[y]++;
            inDegree[x]++;

            if (infos[x] == null) {
                infos[x] = new ArrayList<>();
            }
            infos[x].add(new Info(y, num));
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        Queue<Integer> q = new LinkedList<>();
        q.add(N);
        numOfParts[N] = 1;

        while (!q.isEmpty()) {
            int root = q.poll();
            int required = numOfParts[root];

            for (Info adj : getAdjacent(root)) {
                if (--outDegree[adj.y] == 0) {
                    q.add(adj.y);
                }
                numOfParts[adj.y] += adj.num * required;
            }
        }
        
        IntStream.rangeClosed(1, N)
                .filter(p -> inDegree[p] == 0)
                .mapToObj(p -> String.format("%d %d", p, numOfParts[p]))
                .forEach(System.out::println);
    }

    private static List<Info> getAdjacent(int x) {
        List<Info> result = new ArrayList<>();

        if (infos[x] != null) {
            result.addAll(infos[x]);
        }

        return result;
    }
}

// y 가 num 개 필요
class Info {

    final int y, num;

    public Info(int y, int num) {
        this.y = y;
        this.num = num;
    }
}
