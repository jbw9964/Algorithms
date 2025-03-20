import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, START, END;
    private static List<Info>[] graph, reverseGraph;
    private static int[] inDegree;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        reverseGraph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        inDegree = new int[N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            inDegree[v2]++;
            graph[v1].add(new Info(v2, cost));
            reverseGraph[v2].add(new Info(v1, cost));
        }

        st = new StringTokenizer(br.readLine());
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());
    }

    public static void main(String[] args) throws IOException {
        init();

        int[] time = new int[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(START);

        while (!q.isEmpty()) {
            int from = q.poll();

            for (Info adj : graph[from]) {
                int to = adj.next;
                if (--inDegree[to] == 0) {
                    q.add(to);
                }
                time[to] = Math.max(time[to], adj.cost + time[from]);
            }
        }

        q.add(END);
        boolean[] appended = new boolean[N + 1];
        appended[END] = true;

        int roadCnt = 0;
        while (!q.isEmpty()) {
            int from = q.poll();

            for (Info adj : reverseGraph[from]) {
                int to = adj.next;

                if (time[from] - time[to] == adj.cost) {
                    roadCnt++;

                    if (!appended[to]) {
                        appended[to] = true;
                        q.add(to);
                    }
                }
            }
        }

        System.out.println(time[END]);
        System.out.println(roadCnt);
    }


}

class Info {
    final int next, cost;

    public Info(int next, int cost) {
        this.next = next;
        this.cost = cost;
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