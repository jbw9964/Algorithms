import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static boolean[] horizontalVisit;   // - direction
    private static boolean[] verticalVisit;     // | direction
    private static boolean[] d1Visit;           // \ direction
    private static boolean[] d2Visit;           // / direction

    private static void init()  throws IOException {
        N = Integer.parseInt(br.readLine());
        horizontalVisit = new boolean[N];
        verticalVisit = new boolean[N];
        d1Visit = new boolean[2 * N - 1];
        d2Visit = new boolean[2 * N - 1];
    }

    public static void main(String[] args) throws IOException {

        init();

        backtrack(0);

        System.out.println(ANSWER);
    }

    private static int ANSWER = 0;

    public static void backtrack(int row)   {

        if (row == N)   {
            ANSWER++;
            return;
        }

        for (int c = 0; c < N; c++) {
            if (!placeable(row, c)) {
                continue;
            }

            visit(row, c);
            backtrack(row + 1);
            unVisit(row, c);
        }
    }

    public static boolean placeable(int r, int c)   {
        return !horizontalVisit[r] && !verticalVisit[c] &&
                !d1Visit[toD1(r, c)] && !d2Visit[toD2(r, c)];
    }

    public static void visit(int r, int c) {
        horizontalVisit[r] = true;
        verticalVisit[c] = true;
        d1Visit[toD1(r, c)] = true;
        d2Visit[toD2(r, c)] = true;
    }

    public static void unVisit(int r, int c) {
        horizontalVisit[r] = false;
        verticalVisit[c] = false;
        d1Visit[toD1(r, c)] = false;
        d2Visit[toD2(r, c)] = false;
    }

    // \ direction
    public static int toD1(int r, int c) {
        return r - c + N - 1;
    }

    // / direction
    public static int toD2(int r, int c) {
        return r + c;
    }
}

