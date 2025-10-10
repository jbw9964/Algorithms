import java.util.*;

class Solution {

    private static int inspectHeight(long num) {

        if (num < 0)   {
            throw new IllegalArgumentException();
        }
        if (num == 0)   {
            return 0;
        }

        int height = 0;
        long threshold;
        while (
                num >= (threshold = 0b1L << getNumOfNodesForHeight(height)) &&
                threshold > 0
        )  {
            height++;
        }

        return height;
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {

            long num = numbers[i];
            int height = inspectHeight(num);
            
            int ans = isValid(num, height) ? 1 : 0;
            answer[i] = ans;
        }

        return answer;
    }

    private static boolean isValid(long n, int height) {

        if (height <= 1) {
            return true;
        }

        long l = getLFrom(n, height);
        long r = getRFrom(n, height);

        if (isRootEmpty(n, height)) {
            return l == 0 && r == 0;
        }

        return isValid(l, height - 1) && isValid(r, height - 1);
    }

    private static boolean isRootEmpty(long num, int height) {
        num >>>= getNumOfNodesForHeight(height - 1);
        return (num & 0b1L) == 0;
    }

    private static long getLFrom(long n, int height) {
        return n >>> (getNumOfNodesForHeight(height - 1) + 1);
    }

    private static long getRFrom(long n, int height) {
        return n & (
                -1L + (0b1L << getNumOfNodesForHeight(height - 1))
        );
    }

    private static long getNumOfNodesForHeight(int height) {

        if (height == 0)    {
            return 0;
        }

        long numOfNodes = 1;
        while (height-- > 1) {
            numOfNodes <<= 1;
            numOfNodes |= 0b1L;
        }
        return numOfNodes;
    }
}
