public class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        for (int x = 0; x < r2; x++)
            answer += getLowerCount(r2, x);

        for (int x = 0; x < r1; x++)    {
            int lower = getLowerCount(r1, x);
            if (checkBoundary(r1, x))   lower--;
            answer -= lower;
        }

        return 4 * answer;
    }

    private int getLowerCount(long r, long x) {
        long ySquare = r * r - x * x;
        double y = Math.sqrt(ySquare);

        return (int) y;
    }

    private boolean checkBoundary(long r, long x) {
        long ySquare = r * r - x * x;
        double y = Math.sqrt(ySquare);

        return (long) y * (long) y == ySquare;
    }
}

//  x^2 + y^2 = r^2;
//  y^2 = r^2 - x^2

// 0: 0, 1, 2, 3, 4, 5      -> 6
// 1: 0, 1, 2, 3, 4         -> 5
// 2: 0, 1, 2, 3, 4         -> 5
// 3: 0, 1, 2, 3, 4         -> 5
// 4: 0, 1, 2, 3            -> 4
// 5: 0                     -> 1