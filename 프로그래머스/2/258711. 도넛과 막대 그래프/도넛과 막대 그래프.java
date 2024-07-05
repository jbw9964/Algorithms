import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class Solution {
    private static Integer ROOT_NODE;
    private static final int DONUT = 1;
    private static final int STICK = 2;
    private static final int EIGHT = 3;

    private static Map<Integer, Queue<Integer>> adjacentMap;

    private static void init(int[][] edges)  {
        ROOT_NODE = null;
        Set<Integer> nodesIndeg = new HashSet<>();
        Set<Integer> nodesOutdeg = new HashSet<>();

        adjacentMap = new HashMap<>();
        
        for (int[] edge : edges)    {
            int v1 = edge[0];
            int v2 = edge[1];

            nodesOutdeg.add(v1);
            nodesIndeg.add(v2);

            if (!adjacentMap.containsKey(v1))   adjacentMap.put(v1, new LinkedList<>());
            Queue<Integer> adjacents = adjacentMap.get(v1);
            adjacents.add(v2);
        }

        nodesOutdeg.removeAll(nodesIndeg);
        nodesIndeg.addAll(nodesOutdeg);

        for (int node : nodesOutdeg)    {
            if (adjacentMap.get(node).size() >= 2)  {
                ROOT_NODE = node;
                break;
            }
        }

        ROOT_NODE = ROOT_NODE == null ? nodesOutdeg.toArray(new Integer[0])[0] : ROOT_NODE;
    }

    public int[] solution(int[][] edges) {
        init(edges);

        System.out.println(ROOT_NODE);

        int[] answer = new int[4];
        answer[0] = ROOT_NODE;

        for (int node : adjacentMap.get(ROOT_NODE))
        answer[getGraphType(node)]++;

        return answer;
    }

    private static int getGraphType(int node)   {
        if (!adjacentMap.containsKey(node))     return STICK;

        int numOfVertex = 1;

        Queue<Integer> queue = new LinkedList<>();
        for (int neighbor : adjacentMap.get(node))
        queue.add(neighbor);

        int numOfEdges = queue.size();
        Set<Integer> visitSet = new HashSet<>(queue);


        while (!queue.isEmpty())    {
            int current = queue.poll();

            if (current == node)    continue;

            numOfVertex++;
            if (!adjacentMap.containsKey(current))      continue;

            for (int neighbor : adjacentMap.get(current))   {
                numOfEdges++;

                if (visitSet.contains(neighbor))        continue;

                visitSet.add(neighbor);
                queue.add(neighbor);
            }
        }

        return numOfVertex == numOfEdges ? 
        DONUT : numOfVertex + 1 == numOfEdges ? 
        EIGHT : STICK;
    }
}