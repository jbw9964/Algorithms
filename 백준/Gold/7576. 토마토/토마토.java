import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {
    private static class Node {
        int r, c;
        Node(int r, int c)  {this.r = r; this.c = c;}
        @Override
        public String toString() {
            return String.format("[%d, %d]", r, c);
        }
    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[][] tomatoArray;
    private static boolean[][] visitTable;

    private static Deque<Node> deque = new LinkedList<>();
    private static int[] dr = {0, 0, 1, -1};
    private static int[] dc = {1, -1, 0, 0};


    public static void main(String[] args) throws IOException {
        input();

        int count = 0;
        while (BFS())   count++;
        
        boolean flag = true;
        for (int i = 0; i < N; i++)
        for (int j = 0; j < M; j++)
        if (tomatoArray[i][j] == 0) {flag = false; break;}
        
        System.out.println(flag ? count : "-1");
    }

    public static boolean BFS() {
        Deque<Node> newDeque = new LinkedList<>();

        while (!deque.isEmpty())    {
            Node currentNode = deque.pollFirst();

            for (int i = 0; i < 4; i++) {
                int r = currentNode.r + dr[i];
                int c = currentNode.c + dc[i];

                if (!inRange(r, c))                 continue;
                else if (tomatoArray[r][c] != 0)    continue;
                else if (visitTable[r][c])          continue;

                visitTable[r][c] = true;
                tomatoArray[r][c] = 1;
                newDeque.addFirst(new Node(r, c));
            }
        }

        deque = newDeque;

        return !deque.isEmpty();
    }

    private static boolean inRange(int r, int c)    {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

    public static void input() throws IOException {
        String[] lineInput = br.readLine().split(" ");

        M = Integer.parseInt(lineInput[0]);
        N = Integer.parseInt(lineInput[1]);

        tomatoArray = new int[N][M];
        visitTable = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            lineInput = br.readLine().split(" ");
            for (int j = 0; j < lineInput.length; j++)  {
                int val = Integer.parseInt(lineInput[j]);
                tomatoArray[i][j] = val;

                if (val == 1)   {
                    deque.add(new Node(i, j));
                    visitTable[i][j] = true;
                }
            }
        }
    }
    public static void showTomatoArray() {
        int[][] array = tomatoArray;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
            sb.append(array[i][j] + "\t");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
    public static void showVisitTable() {
        boolean[][] array = visitTable;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)
            sb.append(array[i][j] + "\t");
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
