import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[][] table;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        table = new int[N][];
        for (int i = 0; i < N; i++) {
            table[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = Integer.MIN_VALUE;
        Type[] types = Type.values();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {

                for (Type t : types) {

                    List<Cord> check = t.getBaseCords();

                    LOOP_CHECK:
                    for (int rotate = 0; rotate < 4; rotate++) {
                        int sum = 0;
                        check.forEach(Cord::rotate);

                        for (Cord cord : check) {
                            int checkR = r + cord.r;
                            int checkC = c + cord.c;

                            if (!inRange(checkR, checkC)) {
                                continue LOOP_CHECK;
                            }

                            sum += table[checkR][checkC];
                        }

                        answer = Math.max(answer, sum);
                    }
                }
            }
        }

        System.out.println(answer);
    }

    private static boolean inRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

}

enum Type {
    A(Arrays.asList(
            new Cord(0, 0), new Cord(0, 1),
            new Cord(0, 2), new Cord(0, 3)
    )),
    B(Arrays.asList(
            new Cord(0, 0), new Cord(0, 1),
            new Cord(1, 0), new Cord(1, 1)
    )),
    C1(Arrays.asList(
            new Cord(0, 0), new Cord(1, 0),
            new Cord(2, 0), new Cord(2, 1)
    )),
    C2(Arrays.asList(
            new Cord(0, 0), new Cord(1, 0),
            new Cord(2, 0), new Cord(2, -1)
    )),
    D1(Arrays.asList(
            new Cord(0, 0), new Cord(1, 0),
            new Cord(1, 1), new Cord(2, 1)
    )),
    D2(Arrays.asList(
            new Cord(0, 0), new Cord(1, 0),
            new Cord(1, -1), new Cord(2, -1)
    )),
    E(Arrays.asList(
            new Cord(0, 0), new Cord(0, 1),
            new Cord(0, 2), new Cord(1, 1)
    ));

    final List<Cord> baseCords;

    Type(List<Cord> baseCords) {
        this.baseCords = baseCords;
    }

    public List<Cord> getBaseCords() {
        return baseCords;
    }
}

class Cord {

    int r, c;
    private static final int[][] rotationMatrix = {
            {0, -1},
            {1, 0}
    };

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }


    public void rotate() {

        int nextR = r * rotationMatrix[0][0] + c * rotationMatrix[0][1];
        int nextC = r * rotationMatrix[1][0] + c * rotationMatrix[1][1];

        r = nextR;
        c = nextC;
    }

    @Override
    public String toString() {
        return "Cord{" +
               "r=" + r +
               ", c=" + c +
               '}';
    }
}
