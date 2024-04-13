import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    private static Queue<int[]> list = new LinkedList<>();
    private static final int[] dr = {0, 0, -1, 1};
    private static final int[] dc = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());

            int M = Integer.parseInt(tokenizer.nextToken());
            int N = Integer.parseInt(tokenizer.nextToken());
            int K = Integer.parseInt(tokenizer.nextToken());

            boolean[][][] table = genTable(N, M, K);    // [0] : normal table   |   [1] : visit table
            
            int count = 0;
            while (!list.isEmpty()) {
                int[] coord = list.poll();
                int r = coord[0];
                int c = coord[1];

                if (table[1][r][c]) continue;
                
                table[1][r][c] = true;
                visitBFS(coord, table);
                count++;
            }

            sb.append(count + "\n");
        }

        System.out.println(sb.toString());
    }

    public static void visitBFS(int[] coord, boolean[][][] table)   {
        int N = table[0].length;
        int M = table[0][0].length;

        boolean[][] coordTable = table[0];      // normal table
        boolean[][] visitTable = table[1];      // visit table

        Queue<int[]> queue = new LinkedList<>();
        queue.add(coord);

        while (!queue.isEmpty())    {
            coord = queue.poll();
            int r = coord[0];
            int c = coord[1];

            for (int i = 0; i < 4; i++) {
                int newR = r + dr[i];
                int newC = c + dc[i];

                if (newR < 0 || newR >= N)      continue;
                if (newC < 0 || newC >= M)      continue;
                if (!coordTable[newR][newC])    continue;
                if (visitTable[newR][newC])     continue;

                visitTable[newR][newC] = true;
                queue.add(new int[] {newR, newC});
            }
        }
    }

    public static boolean[][][] genTable(int N, int M, int K) throws IOException {
        boolean[][][] table = new boolean[2][N][M];     // [0] : normal table   |   [1] : visit table

        for (int i = 0; i < K; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            table[0][y][x] = true;
            list.add(new int[] {y, x});
        }

        return table;
    }
}
