import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static boolean[][] moveableTable, visitTable;

    private static int R, C;
    private static boolean inRange(int r, int c)    {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static int[][] getCoord(int r, int c)   {
        int[][] result = {
            {r, c}, {r, c},
            {r, c}, {r, c}
        };

        r = result[0][0];
        while (inRange(r - 1, c) && moveableTable[r - 1][c])    r--;
        result[0][0] = r;

        r = result[1][0];
        while (inRange(r + 1, c) && moveableTable[r + 1][c])    r++;
        result[1][0] = r;

        r = result[2][0];
        c = result[2][1];
        while (inRange(r, c - 1) && moveableTable[r][c - 1])    c--;
        result[2][1] = c;

        c = result[3][1];
        while (inRange(r, c + 1) && moveableTable[r][c + 1])    c++;
        result[3][1] = c;

        return result;
    }

    public int solution(String[] board) {
        R = board.length;
        C = board[0].length();

        int[] initCoord = null;
        int[] targetCoord = null;

        moveableTable = new boolean[R][C];
        for (int i = 0; i < R; i++)     {
            char[] column = board[i].toCharArray();
            for (int j = 0; j < C; j++) {
                if (column[j] == 'D')   continue;
                moveableTable[i][j] = true;

                if (column[j] == 'R')   initCoord = new int[]{i, j, 0};
                if (column[j] == 'G')   targetCoord = new int[]{i, j, 0};
            }
        }

        visitTable = new boolean[R][C];
        visitTable[initCoord[0]][initCoord[1]] = true;

        Queue<int[]> queue = new LinkedList<>();
        queue.add(initCoord);

        while (!queue.isEmpty())    {
            int[] current = queue.poll();

            for (int[] newCoord : getCoord(current[0], current[1]))     {
                int r = newCoord[0];
                int c = newCoord[1];

                if (visitTable[r][c])       continue;
                if (r == targetCoord[0] && c == targetCoord[1]) return current[2] + 1;

                visitTable[r][c] = true;
                queue.add(new int[]{r, c, current[2] + 1});
            }
        }

        return -1;
    }
}