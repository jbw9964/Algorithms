import java.io.*;
import java.util.*;

public class Solution {

    private static int[] diffs, times;
    private static long limit;

    public int solution(int[] diffs, int[] times, long limit) {
        Solution.diffs = diffs.clone();
        Solution.times = times.clone();
        Solution.limit = limit;

        int maxima = Arrays.stream(diffs).max().orElseThrow(
                () -> new RuntimeException("Maximum number of differences is " + limit)
        );

        return binarySearch(1, maxima);
    }


    public int binarySearch(int left, int right)    {

        long leftVal = calc(left);

        int mid = (left + right) / 2;

        if (mid == left)    {
            return leftVal <= Solution.limit ? left : left + 1;
        }

        long midVal = calc(mid);

        return midVal <= Solution.limit ?
                binarySearch(left, mid) : binarySearch(mid, right);
    }

    public static long calc(int level)  {
        long total = Solution.times[0];

        for (int i = 1; i < Solution.diffs.length; i++) {
            long diff = Solution.diffs[i];
            long timeCur = Solution.times[i];
            long timePrev = Solution.times[i - 1];

            long addition = (timePrev + timeCur) * Math.max(diff - level, 0)
                    + timeCur;
            total += addition;
        }

        return total;
    }
}