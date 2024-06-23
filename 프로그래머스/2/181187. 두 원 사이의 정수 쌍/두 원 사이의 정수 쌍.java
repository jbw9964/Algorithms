
class Solution {
    public long solution(int r1, int r2) {
        return getBounded(r2) - getUnBounded(r1);
    }

    private static long getBounded(long r) {
        long total = (2 * r + 1) * (2 * r + 1);

        long x = r;
        long del = r;
        while (x-- > 1) {
            double yCoord = y(r, x);
            del += r - (long) yCoord;
        }

        return total - 4 * del;
    }

    private static long getUnBounded(long r)    {
        long sum = r - 1;

        long x = 0;
        while (x++ < r - 1) {
            double yCoord = Math.ceil(y(r, x));
            sum += (long) yCoord - 1;
        }

        return 4 * sum + 1;
    }

    private static double y(long r, long x)  {
        return Math.sqrt(r * r - x * x);
    }
}
