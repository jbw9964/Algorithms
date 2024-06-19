import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visitTable = new boolean[n];

        int answer = 0;
        for (int node = 0; node < n; node++)    {
            if (visitTable[node])               continue;

            BFS(node, visitTable, computers);
            answer++;
        }
        
        return answer;
    }

    private static void BFS(int init, boolean[] visitTable, int[][] computers)  {
        int n = visitTable.length;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(init);

        visitTable[init] = true;

        while (!queue.isEmpty())    {
            int current = queue.poll();

            for (int i = 0; i < n; i++)     {
                if (i == current)                   continue;
                if (computers[current][i] != 1)     continue;
                if (visitTable[i])                  continue;

                visitTable[i] = true;
                queue.add(i);
            }
        }
    }
}