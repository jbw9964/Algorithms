import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, V;
    private static Map<Integer, List<Integer>> adjacentMap;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        V = Integer.parseInt(tokenizer.nextToken());

        adjacentMap = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(tokenizer.nextToken());
            int v2 = Integer.parseInt(tokenizer.nextToken());

            if (!adjacentMap.containsKey(v1))
                adjacentMap.put(v1, new ArrayList<>(15));

            adjacentMap.get(v1).add(v2);

            if (!adjacentMap.containsKey(v2))
                adjacentMap.put(v2, new ArrayList<>(15));

            adjacentMap.get(v2).add(v1);
        }

        for (List<Integer> neighbors : adjacentMap.values())
            neighbors.sort(Integer::compareTo);

        StringBuilder dfsTraversal = new StringBuilder();
        DFS(V, new boolean[N + 1], dfsTraversal);
        String bfsTraversal = BFS(V);

        System.out.println(dfsTraversal.toString());
        System.out.println(bfsTraversal);
    }

    private static String BFS(int init)   {
        boolean[] visitTable = new boolean[N + 1];
        visitTable[init] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(init);

        StringBuilder traversal = new StringBuilder();

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            traversal.append(curr).append(" ");

            List<Integer> neighbors = adjacentMap.get(curr);
            if (neighbors == null)          continue;

            for (int neighbor : neighbors)
                if (visitTable[neighbor])   continue;
                else                        {
                    visitTable[neighbor] = true;
                    queue.add(neighbor);
                }
        }

        traversal.deleteCharAt(traversal.length() - 1);

        return traversal.toString();
    }

    private static void DFS(
            int node, boolean[] visitTable, StringBuilder traversal
    )     {
        visitTable[node] = true;
        traversal.append(node).append(" ");

        List<Integer> neighbors = adjacentMap.get(node);
        if (neighbors == null)      return;

        for (int neighbor : neighbors)
            if (visitTable[neighbor])   continue;
            else                        DFS(neighbor, visitTable, traversal);
    }
}