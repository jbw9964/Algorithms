import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final BufferedWriter bw = new BufferedWriter(
        new OutputStreamWriter(System.out)
    );

    private static boolean[][] Array;       // true : water | false : ice
    private static boolean[][] VisitTable;
    private static int R, C;

    private static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    static class Coord {
        int row, col;
        public Coord(int i, int j)  {this.row = i; this.col = j;}
    }

    private static Coord TargetCoord;
    private static Queue<Coord> InitCoords = new ArrayDeque<>();
    private static Queue<Coord> nextInits = new ArrayDeque<>();
    private static Queue<Coord> tempQueue = new ArrayDeque<>();

    private static boolean inRange(int row, int col) {
        return 0 <= row && row < R && 0 <= col && col < C;
    }


    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");

        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);

        Array = new boolean[R][C];
        VisitTable = new boolean[R][C];

        Coord[] coords = null;

        for (int i = 0; i < R; i++) {
            input = br.readLine().split("");

            for (int j = 0; j < C; j++) {
                if (input[j].equals("X"))   continue;

                Array[i][j] = true;
                tempQueue.add(new Coord(i, j));

                if (input[j].equals("L")) {
                    if (coords != null)   {coords[1] = new Coord(i, j);  continue;}
                    
                    coords = new Coord[2];
                    coords[0] = new Coord(i, j);
                }
            }
        }

        TargetCoord = coords[0];
        InitCoords.add(coords[1]);

        int days = 0;
        do {
            meltIce();
            days++;
        } while (!BFS());

        bw.write(String.valueOf(days));
        bw.flush();
    }

    private static void meltIce() {
        Queue<Coord> tempQueue_local = new ArrayDeque<>();
        
        Coord check;
        int row, col;

        while ((check = tempQueue.poll()) != null) {
            row = check.row;
            col = check.col;

            for (int i = 0; i < 4; i++) {
                if (!inRange(row + dx[i], col + dy[i]) || Array[row + dx[i]][col + dy[i]]) continue;

                Array[row + dx[i]][col + dy[i]] = true;
                tempQueue_local.add(new Coord(row + dx[i], col + dy[i]));
            }
        }

        tempQueue = tempQueue_local;
    }

    private static boolean BFS() {
        Coord current;
        int row, col;

        while ((current = InitCoords.poll()) != null) {
            row = current.row;
            col = current.col;
            if (row == TargetCoord.row && col == TargetCoord.col)   return true;
            
            VisitTable[row][col] = true;

            for (int i = 0; i < 4; i++) {
                if (!inRange(row + dx[i], col + dy[i]) 
                    || VisitTable[row + dx[i]][col + dy[i]])    continue;

                VisitTable[row + dx[i]][col + dy[i]] = true;

                if (Array[row + dx[i]][col + dy[i]]) {
                    InitCoords.add(new Coord(row + dx[i], col + dy[i]));
                    continue;
                }
                
                nextInits.add(new Coord(row + dx[i], col + dy[i]));
            }
        }

        InitCoords = nextInits;
        nextInits = new ArrayDeque<Coord>();

        return false;
    }
}