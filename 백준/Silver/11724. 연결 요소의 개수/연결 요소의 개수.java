import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int N, M;
    private static boolean[]        visitTable;
    private static Queue<Integer>[] adjacentList;
    private static Queue<Integer>   queue;

    public static void main (String[] args) throws IOException {
        input();

        int count = 0;
        for (int vertex = 1; vertex <= N; vertex++) {
            if (visitTable[vertex])     continue;

            DFSTraversal(vertex);
            count++;
        }

        System.out.println(count);
    }

    public static void DFSTraversal(int initVertex) {
        visitTable[initVertex] = true;
        queue.add(initVertex);

        while (!queue.isEmpty())    {
            int vertex = queue.poll();

            Queue<Integer> adjacents = adjacentList[vertex];

            if (adjacents == null)      continue;

            Iterator<Integer> iter = adjacents.iterator();

            while (iter.hasNext())  {
                int adjacent = iter.next();

                if (visitTable[adjacent])   continue;

                visitTable[adjacent] = true;
                queue.add(adjacent);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void input() throws IOException {
        String[] lineInput = br.readLine().split(" ");

        N = Integer.parseInt(lineInput[0]);
        M = Integer.parseInt(lineInput[1]);

        visitTable = new boolean[N + 1];
        adjacentList = new Deque[N + 1];
        queue = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            lineInput = br.readLine().split(" ");

            int u = Integer.parseInt(lineInput[0]);
            int v = Integer.parseInt(lineInput[1]);

            if (adjacentList[u] == null)    adjacentList[u] = new ArrayDeque<>();
            if (adjacentList[v] == null)    adjacentList[v] = new ArrayDeque<>();

            adjacentList[u].add(v);
            adjacentList[v].add(u);
        }
    }
}
