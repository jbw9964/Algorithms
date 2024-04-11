import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    private static class Node {
        int vertexIndex, prevIndex;
        Node(int vertexIndex, int prevIndex)    {
            this.vertexIndex = vertexIndex;
            this.prevIndex = prevIndex;
        }
    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int N, M;
    private static boolean[]    visitTable;
    private static Deque<Integer>[] adjacentList;

    public static void main (String[] args) throws IOException {
        input();

        int count = 0;
        for (int vertex = 1; vertex <= N; vertex++) {
            if (visitTable[vertex])     continue;

            BFSConnection(vertex);
            count++;
        }

        System.out.println(count);
    }

    public static boolean BFSConnection(int initVertex) {
        visitTable[initVertex] = true;

        Queue<Node> vertexToTrav = new LinkedList<>();
        vertexToTrav.add(new Node(initVertex, 0));

        boolean flag = false;
        while (!vertexToTrav.isEmpty()) {
            Node currentVertex = vertexToTrav.poll();
            int currentIndex = currentVertex.vertexIndex;
            int prevIndex = currentVertex.prevIndex;

            Deque<Integer> adjacents = adjacentList[currentIndex];

            if (adjacents == null)      continue;

            for (int i = 0; i < adjacents.size(); i++) {
                int adjacentVertex = adjacents.pollFirst();
                adjacents.add(adjacentVertex);

                if (adjacentVertex == prevIndex)        continue;
                else if (visitTable[adjacentVertex])    {flag = true; continue;}

                visitTable[adjacentVertex] = true;
                vertexToTrav.add(new Node(adjacentVertex, currentIndex));
            }
        }

        return flag;
    }

    @SuppressWarnings("unchecked")
    public static void input() throws IOException {
        String[] lineInput = br.readLine().split(" ");

        N = Integer.parseInt(lineInput[0]);
        M = Integer.parseInt(lineInput[1]);

        visitTable = new boolean[N + 1];
        adjacentList = new Deque[N + 1];

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
