import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, start, end;
    private static int[] inDegree;
    private static int[][] costTable;
    private static List<Integer>[] adjacent;
    private static List<Integer>[] reverseAdjacent;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        inDegree = new int[N + 1];
        costTable = new int[N + 1][N + 1];
        adjacent = new ArrayList[N + 1];
        reverseAdjacent = new ArrayList[N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            inDegree[to]++;
            costTable[from][to] = time;
            recordAdjacent(from, to, adjacent);
            recordAdjacent(to, from, reverseAdjacent);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    private static void recordAdjacent(int from, int to, List<Integer>[] adjArr) {
        if (adjArr[from] == null) {
            adjArr[from] = new ArrayList<>();
        }
        adjArr[from].add(to);
    }

    public static void main(String[] args) throws IOException {
        init();

        int[] lastTimeTable = new int[N + 1];

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int root = q.poll();
            int currentTime = lastTimeTable[root];

            for (int adj : getAdjacent(root, adjacent)) {
                if (--inDegree[adj] == 0) {
                    q.add(adj);
                }

                lastTimeTable[adj] = Math.max(costTable[root][adj] + currentTime, lastTimeTable[adj]);
            }
        }

        // 백트래킹
        q.add(end);

        int roadCnt = 0;
        Set<Integer> visited = new HashSet<>();
        visited.add(end);

        while (!q.isEmpty()) {
            int to = q.poll();

            for (int from : getAdjacent(to, reverseAdjacent)) {
                if (lastTimeTable[to] - lastTimeTable[from] == costTable[from][to]) {
                    roadCnt++;

                    if (visited.add(from))    {
                        q.add(from);
                    }
                }
            }
        }

        System.out.println(lastTimeTable[end]);
        System.out.println(roadCnt);
    }

    private static List<Integer> getAdjacent(int v, List<Integer>[] adjArr) {
        List<Integer> result = new ArrayList<>();

        if (adjArr[v] != null) {
            result.addAll(adjArr[v]);
        }

        return result;
    }
}

/*
       (4)         (5)
     /-----> 2------------> 7
    /    (3)  \            /  (5)
   /            \-------> 6
  /                       ^
 / (2)       (1)    (2)  /|
1 ----> 3-------------> 5 |  (4)
   \          (3)         |
    \-------------------> 4
 */