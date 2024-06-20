import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Coord {
    int r, c;
    Coord(int r, int c) {this.r = r; this.c = c;}

    @Override
    public String toString() {
        return String.format("[%d, %d]", r, c);
    }
}

class Block {
    private int size;
    private boolean[][] blockShape;
    private int blockR, blockC;

    private static int R, C;
    private static final int[] dr = {0, 0, 1, -1}, dc = {1, -1, 0, 0};
    private boolean inRange(int r, int c)   {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    public Block(
        int[][] board, boolean[][] visitTable, 
        int init_r, int init_c
    ) {
        R = board.length;
        C = board[0].length;
        int blockValue = board[init_r][init_c];

        visitTable[init_r][init_c] = true;

        Queue<Coord> queue = new LinkedList<>();
        queue.add(new Coord(init_r, init_c));

        Queue<Coord> adjacents = new LinkedList<>();
        int minColumn, maxColumn, minRow, maxRow;
        minColumn = minRow = Integer.MAX_VALUE;
        maxColumn = maxRow = Integer.MIN_VALUE;

        while (!queue.isEmpty())    {
            Coord current = queue.poll();
            adjacents.add(current);

            minColumn = Math.min(minColumn, current.c);
            maxColumn = Math.max(maxColumn, current.c);
            minRow = Math.min(minRow, current.r);
            maxRow = Math.max(maxRow, current.r);

            for (int i = 0; i < 4; i++)     {
                int r = current.r + dr[i];
                int c = current.c + dc[i];

                if (!inRange(r, c))             continue;
                if (board[r][c] != blockValue)  continue;
                if (visitTable[r][c])           continue;

                visitTable[r][c] = true;
                queue.add(new Coord(r, c));
            }
        }

        blockR = maxRow - minRow + 1;
        blockC = maxColumn - minColumn + 1;
        size = adjacents.size();
        blockShape = new boolean[blockR][blockC];

        for (Coord coord : adjacents)
        blockShape[coord.r - minRow][coord.c - minColumn] = true;
    }   

    public void rotate()    {
        boolean[][] newBlockShape = new boolean[blockC][blockR];

        for (int i = 0; i < blockR; i++)
        for (int j = 0; j < blockC; j++)
        newBlockShape[j][blockR - i - 1] = blockShape[i][j];

        int temp = blockR;
        blockR = blockC;
        blockC = temp;

        blockShape = newBlockShape;
    }

    public void showBlock()     {
        StringBuilder sb = new StringBuilder();
        sb.append("-".repeat(blockC + 4)).append("\n");
        for (int i = 0; i < blockR; i++)    {
            sb.append("| ");
            for (int j = 0; j < blockC; j++)
            sb.append(blockShape[i][j] ? "*" : " ");
            sb.append(" |\n");
        }

        sb.append("-".repeat(blockC + 4)).append("\n");

        System.out.println(sb.toString());
    }

    @Override
    public boolean equals(Object obj)   {
        if (this == obj)                return true;

        if (obj == null || obj.getClass() != Block.class)   return false;

        Block casted = (Block) obj;

        if (this.blockR != casted.blockR || this.blockC != casted.blockC)   return false;

        for (int i = 0; i < blockR; i++)
        for (int j = 0; j < blockC; j++)
        if (this.blockShape[i][j] != casted.blockShape[i][j])
        return false;

        return true;
    }

    public int getSize()    {return size;}
}

class Solution {
    @SuppressWarnings("unchecked")
    private static Queue<Block>[] init(int[][] game_board, int[][] table) {
        int R = game_board.length;
        int C = game_board[0].length;

        boolean[][] visitTable = new boolean[R][C];
        Queue<Block> tempBlockQueue = new LinkedList<>();

        for (int i = 0; i < R; i++)
        for (int j = 0; j < C; j++)     {
            if (game_board[i][j] != 0)  continue;
            if (visitTable[i][j])       continue;

            tempBlockQueue.add(new Block(game_board, visitTable, i, j));
        }

        Block[] emptyBlocks = tempBlockQueue.toArray(new Block[0]);
        Arrays.sort(emptyBlocks, new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o2.getSize() - o1.getSize();
            }
        });

        Queue<Block> targetQueue = new LinkedList<>();
        for (int i = 0; i < emptyBlocks.length; i++)
        targetQueue.add(emptyBlocks[i]);

        tempBlockQueue.clear();
        visitTable = new boolean[R][C];

        for (int i = 0; i < R; i++)
        for (int j = 0; j < C; j++)     {
            if (table[i][j] != 1)       continue;
            if (visitTable[i][j])       continue;

            tempBlockQueue.add(new Block(table, visitTable, i, j));
        }

        Block[] havingBlocks = tempBlockQueue.toArray(new Block[0]);
        Arrays.sort(havingBlocks, new Comparator<Block>() {
            @Override
            public int compare(Block o1, Block o2) {
                return o2.getSize() - o1.getSize();
            }
        });

        Queue<Block> havingQueue = new LinkedList<>();
        for (int i = 0; i < havingBlocks.length; i++)
        havingQueue.add(havingBlocks[i]);

        return new Queue[] {targetQueue, havingQueue};
    }

    public int solution(int[][] game_board, int[][] table) {
        
        Queue<Block>[] init = init(game_board, table);
        Queue<Block> targetBlocks = init[0];
        Queue<Block> checkBlocks = init[1];

        int answer = 0;
        while (!targetBlocks.isEmpty())     {
            Block target = targetBlocks.poll();

            while (checkBlocks.peek() != null && checkBlocks.peek().getSize() > target.getSize())
            checkBlocks.poll();

            Queue<Block> tempQueue = new LinkedList<>();
            
            WHILE_LOOP:
            while (!checkBlocks.isEmpty() && checkBlocks.peek().getSize() == target.getSize())  {
                Block check = checkBlocks.poll();

                for (int i = 0; i < 4; i++)
                if (!target.equals(check))  target.rotate();
                else                        {
                    answer += target.getSize();
                    break WHILE_LOOP;
                }

                tempQueue.add(check);
            }
            
            tempQueue.addAll(checkBlocks);
            checkBlocks = tempQueue;
        }

        return answer;
    }

}