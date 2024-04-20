import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.IOException;

class Shark {
    public static class Coord {
        int r, c;
        Coord(int r, int c) {this.r = r; this.c = c;}

        public int getRow() {return this.r;}
        public int getCol() {return this.c;}
    }

    static enum Direction   {
        UP(1), DOWN(2), RIGHT(3), LEFT(4);

        private int value;
        private Direction(int value)    {this.value = value;}

        @Override
        public String toString() {
            String str = null;
            switch (value) {
                case 1 : str = "UP";    break;
                case 2 : str = "DOWN";  break;
                case 3 : str = "RIGHT"; break;
                case 4 : str = "LEFT";  break;
            }
            return str;
        }
    }

    private Coord coord;
    private int speed, size;
    private Direction dir;

    public Shark(int r, int c, int s, int d, int z) {
        speed = s;
        size = z;

        coord = new Coord(r, c);

        switch (d) {
            case 1 : dir = Direction.UP;    break;
            case 2 : dir = Direction.DOWN;  break;
            case 3 : dir = Direction.RIGHT; break;
            case 4 : dir = Direction.LEFT;  break;
        }
    }

    private void moveShark() {
        switch (dir) {
            case UP     : coord.r -= 1; break;
            case DOWN   : coord.r += 1; break;
            case RIGHT  : coord.c += 1; break;
            case LEFT   : coord.c -= 1; break;
        }
    }
    public void moveShark(int R, int C) {
        int netMoveR = (speed) % (2 * R - 2);
        int netMoveC = (speed) % (2 * C - 2);

        int count = 0;

        switch (dir) {
            case UP :
            case DOWN :     count = netMoveR;   break;
        
            case LEFT : 
            case RIGHT :    count = netMoveC;   break;
        }

        while (count-- > 0) {

            if (coord.r == 0 && dir == Direction.UP)        dir = Direction.DOWN;
            if (coord.r == R - 1 && dir == Direction.DOWN)  dir = Direction.UP;
            if (coord.c == 0 && dir == Direction.LEFT)      dir = Direction.RIGHT;
            if (coord.c == C - 1 && dir == Direction.RIGHT) dir = Direction.LEFT;

            moveShark();
        }
    }

    public int getSize()    {return this.size;}
    public Coord getCoord() {return this.coord;}
}

class SharkManager  {
    private int R, C;

    private Set<Shark> sharkSet;
    private Shark[][] sharkTable;

    public SharkManager(int R, int C, int M)    {
        this.R = R;
        this.C = C;

        sharkSet = new HashSet<>(2 * M);
        sharkTable = new Shark[R][C];
    }

    public void addShark(int r, int c, int s, int d, int z) {
        Shark newShark = new Shark(r, c, s, d, z);
        sharkSet.add(newShark);
        
        sharkTable[r][c] = newShark;
    }

    public void moveShark() {
        Shark[][] newTable = new Shark[R][C];

        Queue<Shark> removalSharks = new LinkedList<>();

        for (Shark shark : sharkSet)    {
            shark.moveShark(R, C);

            Shark.Coord coord = shark.getCoord();
            int r = coord.getRow();
            int c = coord.getCol();

            if (newTable[r][c] == null)     {newTable[r][c] = shark; continue;}

            Shark tempShark = newTable[r][c];
            Shark sharkToAppend = shark.getSize() > tempShark.getSize() ? shark : tempShark;
            Shark sharkToRemove = shark.getSize() < tempShark.getSize() ? shark : tempShark;

            newTable[r][c] = sharkToAppend;
            removalSharks.add(sharkToRemove);
        }

        this.sharkTable = newTable;

        while (!removalSharks.isEmpty())    {
            Shark sharkToRemove = removalSharks.poll();
            sharkSet.remove(sharkToRemove);
        }
    }

    public int catchShark(int c)    {
        Shark sharkToRemove = null;
        int size = 0;
        
        for (int i = 0; i < R; i++) {
            Shark closestShark = sharkTable[i][c];

            if (closestShark == null)   continue;

            sharkToRemove = closestShark;
            break;
        }

        if (sharkToRemove != null)  {
            sharkSet.remove(sharkToRemove);
            size = sharkToRemove.getSize();
        }

        return size;
    }
}


public class Main {
    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        SharkManager manager = new SharkManager(R, C, M);

        while (M-- > 0) {
            tokenizer = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(tokenizer.nextToken()) - 1;
            int c = Integer.parseInt(tokenizer.nextToken()) - 1;
            int s = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            int z = Integer.parseInt(tokenizer.nextToken());

            manager.addShark(r, c, s, d, z);
        }

        M = 0;

        for (int c = 0; c < C; c++) {
            M += manager.catchShark(c);
            manager.moveShark();
        }

        System.out.println(M);
    }
}