
class Solution {
    private static final int N = 1_000_000;

    public int[] solution(int[][] edges) {
        int[] vertexOutdeg = new int[N + 1];
        int[] vertexIndeg = new int[N + 1];

        for (int[] edge : edges)    {
            vertexOutdeg[edge[0]]++;
            vertexIndeg[edge[1]]++;
        }

        int root = 0;
        int numOfSticks = 0;
        int numOfEights = 0;

        for (int i = 1; i < N; i++)     {
            if (vertexOutdeg[i] >= 2)   {
                if (vertexIndeg[i] == 0)    root = i;
                else                        numOfEights++;
            }
            else if (vertexOutdeg[i] == 0 && vertexIndeg[i] >= 1)
            numOfSticks++;
        }
        
        return new int[] {
            root, 
            vertexOutdeg[root] - numOfSticks - numOfEights,
            numOfSticks, numOfEights
        };
    }
}