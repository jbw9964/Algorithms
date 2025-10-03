
import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int R, C, K;
    private static boolean[][] table;
    private static List<Sticker> stickers;

    private static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        table = new boolean[R][C];
        stickers = new ArrayList<>(K);

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            Sticker newSticker = new Sticker();

            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());

                for (int c = 0; c < C; c++) {
                    int val = Integer.parseInt(st.nextToken());

                    if (val == 1) {
                        newSticker.addCord(new Cord(r, c));
                    }
                }
            }

            stickers.add(newSticker);
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;

        LOOP:
        for (Sticker sticker : stickers) {

            List<Cord> stickerCords = sticker.getCords();

            for (int rotate = 0; rotate < 4; rotate++) {

                for (int baseR = 0; baseR < R; baseR++) {
                    for (int baseC = 0; baseC < C; baseC++) {

                        if (!assignableWith(baseR, baseC, stickerCords))    {
                            continue;
                        }

                        for (Cord cord : stickerCords) {
                            int r = baseR + cord.r;
                            int c = baseC + cord.c;

                            if (!inRange(r, c) || table[r][c]) {
                                throw new RuntimeException();
                            }

                            table[r][c] = true;
                        }

                        answer += stickerCords.size();
                        continue LOOP;
                    }
                }

                sticker.rotate();
            }
        }

        System.out.println(answer);
    }

    private static boolean assignableWith(int baseR, int baseC, Sticker sticker) {

        for (int iter = 0; iter < 4; iter++) {

            List<Cord> stickerCords = sticker.getCords();

            if (assignableWith(baseR, baseC, stickerCords)) {
                return true;
            }

            sticker.rotate();
        }

        return false;
    }

    private static boolean assignableWith(int baseR, int baseC, List<Cord> cords)   {

        for (Cord cord : cords) {

            int r = baseR + cord.r;
            int c = baseC + cord.c;

            if (!inRange(r, c) || table[r][c]) {
                return false;
            }
        }

        return true;
    }
}

class Sticker {

    final List<Cord> cords = new ArrayList<>();

    public Sticker() {
    }

    public void addCord(Cord cord) {
        cords.add(cord);
    }

    public List<Cord> getCords() {
        return cords;
    }

    public void rotate()    {
        cords.forEach(Cord::rotate);
    }

    @Override
    public String toString() {
        return "Sticker{" +
               "cords=" + cords +
               '}';
    }
}

class Cord {

    int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }

    // clockwise 90 degree
    public void rotate() {
        // rotation matrix : [ [cos(t), -sin(t)], [sin(t), cos(t)] ]
        int nextR = c;
        int nextC = -r;

        this.r = nextR;
        this.c = nextC;
    }

    @Override
    public String toString() {
        return "Cord{" +
               "r=" + r +
               ", c=" + c +
               '}';
    }
}
