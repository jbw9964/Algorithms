import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

class Node  {
    int v, dist;
    Node(int v, int dist)   {
        this.v = v;
        this.dist = dist;
    }
}

class Solution {
    public int solution(int n, int[][] edge) {
        Map<Integer, List<Integer>> adjacentMap = new HashMap<>();
        for (int[] info : edge)     {
            int v1 = info[0];
            int v2 = info[1];

            if (!adjacentMap.containsKey(v1))   adjacentMap.put(v1, new LinkedList<>());
            adjacentMap.get(v1).add(v2);

            if (!adjacentMap.containsKey(v2))   adjacentMap.put(v2, new LinkedList<>());
            adjacentMap.get(v2).add(v1);
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));

        boolean[] visitTable = new boolean[n + 1];
        visitTable[1] = true;

        Stack<Node> stack = new Stack<>();
        while (!queue.isEmpty())    {
            Node current = queue.poll();
            stack.push(current);

            for (int adjacent : adjacentMap.get(current.v)) {
                if (visitTable[adjacent])                   continue;

                visitTable[adjacent] = true;
                queue.add(new Node(adjacent, current.dist + 1));
            }
        }

        int answer = 1;
        Node top = stack.pop();

        while (stack.peek().dist == top.dist)   {
            stack.pop();
            answer++;
        }

        return answer;
    }
}