import java.util.Arrays;

public class Solution {
    public int solution(int[] numbers) {
        int N = numbers.length;
        Arrays.sort(numbers);

        int maxima = Integer.MIN_VALUE;

        for (int i = 0; i < N - 1; i++)
            maxima = Math.max(maxima, numbers[i] * numbers[i + 1]);

        return maxima;
    }
}