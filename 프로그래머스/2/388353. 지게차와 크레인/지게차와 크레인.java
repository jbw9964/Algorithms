import java.util.*;
import java.util.function.*;

class Solution {

    private int R, C;
    private static final int[] dr = {-1, 1, 0, 0}, dc = {0, 0, 1, -1};

    private boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }

    private char[][] table;
    private static final char OUTER = '/';
    private static final char EMPTY = ' ';

    private void init(String[] storage) {
        R = storage.length;
        C = storage[0].length();

        table = new char[R][];
        for (int i = 0; i < R; i++) {
            table[i] = storage[i].toCharArray();
        }
    }

    public int solution(String[] storage, String[] requests) {
        init(storage);

        int answer = R * C;
        for (String request : requests) {
            List<Cord> removals = getRemovalCords(request);

            answer -= removals.size();

            fillCordsEmpty(removals);

            updateOuterBlocks();
        }

        return answer;
    }

    private List<Cord> getRemovalCords(String request) {

        List<Cord> removals = new ArrayList<>();

        final char target = request.charAt(0);
        BiPredicate<Integer, Integer> filter = getFilterOnRequest(request, target);

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (target == table[i][j] && filter.test(i, j)) {
                    removals.add(new Cord(i, j));
                }
            }
        }

        return removals;
    }

    private BiPredicate<Integer, Integer> getFilterOnRequest(String request, char target) {
        BiPredicate<Integer, Integer> pass = (r, c) -> target == table[r][c];
        BiPredicate<Integer, Integer> outerRegion = (r, c) -> {
            for (int i = 0; i < dr.length; i++) {
                int adjR = r + dr[i];
                int adjC = c + dc[i];

                if (!inRange(adjR, adjC) || table[adjR][adjC] == OUTER) {
                    return true;
                }
            }
            return false;
        };

        return request.length() >= 2 ? pass : outerRegion;
    }

    private void fillCordsEmpty(List<Cord> cords) {
        for (Cord c : cords) {
            table[c.r][c.c] = EMPTY;
        }
    }

    private void updateOuterBlocks() {

        List<Cord> newOuterBlocks = new ArrayList<>();
        boolean[][] visited = new boolean[R][C];

        for (int c = 0; c < C; c++) {
            if (!visited[0][c] && (table[0][c] == OUTER || table[0][c] == EMPTY)) {
                newOuterBlocks.addAll(getOuterBlocksWithBFS(0, c, visited));
            }
            if (!visited[R - 1][c] && (table[R - 1][c] == OUTER || table[R - 1][c] == EMPTY)) {
                newOuterBlocks.addAll(getOuterBlocksWithBFS(R - 1, c, visited));
            }
        }

        for (int r = 1; r < R - 1; r++) {
            if (!visited[r][0] && (table[r][0] == OUTER || table[r][0] == EMPTY)) {
                newOuterBlocks.addAll(getOuterBlocksWithBFS(r, 0, visited));
            }
            if (!visited[r][C - 1] && (table[r][C - 1] == OUTER || table[r][C - 1] == EMPTY)) {
                newOuterBlocks.addAll(getOuterBlocksWithBFS(r, C - 1, visited));
            }
        }

        for (Cord c : newOuterBlocks) {
            table[c.r][c.c] = OUTER;
        }
    }

    private List<Cord> getOuterBlocksWithBFS(int initR, int initC, boolean[][] visited) {
        List<Cord> outerBlocks = new ArrayList<>();

        visited[initR][initC] = true;

        Queue<Cord> queue = new LinkedList<>();
        queue.add(new Cord(initR, initC));

        while (!queue.isEmpty()) {

            Cord c = queue.poll();
            outerBlocks.add(c);

            for (int i = 0; i < dr.length; i++) {
                int adjR = c.r + dr[i];
                int adjC = c.c + dc[i];

                if (!inRange(adjR, adjC) || (table[adjR][adjC] != OUTER
                        && table[adjR][adjC] != EMPTY) || visited[adjR][adjC]) {
                    continue;
                }

                visited[adjR][adjC] = true;
                queue.add(new Cord(adjR, adjC));
            }
        }

        return outerBlocks;
    }

    private void showTable() {
        for (char[] row : table) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}

class Cord {

    final int r, c;

    public Cord(int r, int c) {
        this.r = r;
        this.c = c;
    }
}