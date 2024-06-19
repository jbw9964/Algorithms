import java.util.Queue;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;

class OilChunk  {
    private static class Coord  {
        int r, c;
        Coord(int r, int c)     {this.r = r; this.c = c;}
    }
    private int size;
    private Set<Integer> columnSet;

    private static int R, C;
    private static int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};

    private boolean inRange(int r, int c)   {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public OilChunk(int[][] land, boolean[][] visitTable, int init_r, int init_c)   {
        R = land.length;
        C = land[0].length;
        size = 0;
        
        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(init_r, init_c));

        visitTable[init_r][init_c] = true;

        columnSet = new HashSet<>();
        
        while (!queue.isEmpty())    {
            Coord current = queue.poll();
            columnSet.add(current.c);
            size++;

            for (int i = 0; i < 4; i++)     {
                int r = current.r + dr[i];
                int c = current.c + dc[i];

                if (!inRange(r, c))         continue;
                if (land[r][c] != 1)        continue;
                if (visitTable[r][c])       continue;

                visitTable[r][c] = true;
                queue.add(new Coord(r, c));
            }
        }
    }

    public boolean contains(int column)     {
        return columnSet.contains(column);
    }

    public int getSize()                    {return size;}
    public Integer[] columnSetArray()       {return columnSet.toArray(new Integer[0]);}
}

class Solution {
    public int solution(int[][] land) {
        int R = land.length;
        int C = land[0].length;
        boolean[][] visitTable = new boolean[R][C];
        int[] countTable = new int[C];

        for (int i = 0; i < R; i++)
        for (int j = 0; j < C; j++)     {
            if (land[i][j] != 1)        continue;
            if (visitTable[i][j])       continue;

            OilChunk newChunk = new OilChunk(land, visitTable, i, j);
            
            for (int column : newChunk.columnSetArray())
            countTable[column] += newChunk.getSize();
        }

        int answer = 0;
        for (int count : countTable)
        answer = Math.max(answer, count);

        return answer;
    }
}