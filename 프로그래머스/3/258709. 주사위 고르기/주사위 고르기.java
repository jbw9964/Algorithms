import java.util.*;

class Solution {

    private static List<Index> indices;

    private static void init(int[][] dice) {
        indices = Combinator.getCombination(dice.length);
    }

    public int[] solution(int[][] dice) {
        init(dice);

        double maxWinRatio = -1.d;
        Index answer = null;

        for (Index i : indices) {

            List<Integer> normalCaseIndices = i.normalCase;
            List<Integer> oppositeCaseIndices = i.oppositeCase;

            List<Integer> normalCaseRecord = new ArrayList<>();
            List<Integer> oppositeCaseRecord = new ArrayList<>();

            for (int ni : normalCaseIndices) {
                int[] d = dice[ni];
                recordCase(d, normalCaseRecord);
            }

            for (int oi : oppositeCaseIndices) {
                int[] d = dice[oi];
                recordCase(d, oppositeCaseRecord);
            }

            Report report = new Report(normalCaseRecord, oppositeCaseRecord);
            double winRatio = report.calcWinRatio();

            if (winRatio > maxWinRatio) {
                maxWinRatio = winRatio;
                answer = i;
            }
        }

        if (answer == null) {
            throw new RuntimeException();
        }

        return answer.normalCase.stream().mapToInt(i -> i + 1).sorted().toArray();
    }

    private static void recordCase(int[] dice, List<Integer> dst) {

        int size = dst.size();

        if (size == 0) {
            for (int d : dice) {
                dst.add(d);
            }
            return;
        }

        while (size-- > 0) {
            int val = dst.remove(0);
            for (int d : dice) {
                dst.add(val + d);
            }
        }
    }

}

class Report {

    final long winCnt, looseCnt, drawCnt;

    public Report(List<Integer> primaryRecord, List<Integer> secondaryRecord) {

        int[] secondary = secondaryRecord.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();

        long wc, lc, dc;
        wc = lc = dc = 0;

        for (int pr : primaryRecord) {
            int lowerCnt = countLower(pr, secondary);
            int higherCnt = countHigher(pr, secondary);
            int equalCnt = secondary.length - lowerCnt - higherCnt;

            wc += lowerCnt;
            lc += higherCnt;
            dc += equalCnt;
        }

        winCnt = wc;
        looseCnt = lc;
        drawCnt = dc;
    }

    public double calcWinRatio() {
        long total = winCnt + looseCnt + drawCnt;
        return (double) winCnt / (double) total;
    }

    private static int countLower(int key, int[] arr) {

        int i = Arrays.binarySearch(arr, key);
        int insertionPoint;

        if (i >= 0) {
            while (i - 1 > 0 && arr[i - 1] == key) {
                i--;
            }
            insertionPoint = i;

        } else {
            insertionPoint = -(i + 1);
        }

        return insertionPoint;
    }

    private static int countHigher(int key, int[] arr) {

        int i = Arrays.binarySearch(arr, key);
        int insertionPoint;

        if (i >= 0) {
            while (i + 1 < arr.length && arr[i + 1] == key) {
                i++;
            }
            insertionPoint = i;
        } else {
            insertionPoint = -(i + 1);
        }

        return arr.length - insertionPoint;
    }
}

class Combinator {

    public static List<Index> getCombination(int n) {
        List<Index> dst = new ArrayList<>();

        getCombination(new boolean[n], 0, -1, dst);

        return dst;
    }

    private static void getCombination(boolean[] used, int cnt, int prevI, List<Index> dst) {

        int n = used.length;

        if (cnt == n / 2) {

            List<Integer> normalCase = new ArrayList<>(n);
            List<Integer> oppositeCase = new ArrayList<>(n);
            for (int i = 0; i < n; i++) {
                if (used[i]) {
                    normalCase.add(i);
                } else {
                    oppositeCase.add(i);
                }
            }

            dst.add(new Index(normalCase, oppositeCase));
            return;
        }

        if (prevI == n - 1) {
            return;
        }

        for (int trial = prevI + 1; trial < n; trial++) {

            if (!used[trial]) {
                used[trial] = true;
                getCombination(used, cnt + 1, trial, dst);
                used[trial] = false;
            }

        }

    }

}

class Index {

    final List<Integer> normalCase, oppositeCase;

    public Index(List<Integer> normalCase, List<Integer> oppositeCase) {
        this.normalCase = normalCase;
        this.oppositeCase = oppositeCase;
    }
}