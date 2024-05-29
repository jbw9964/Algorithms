import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;

        Integer[] boxedScore = Arrays.stream(score).boxed().toArray(Integer[]::new);
        Arrays.sort(boxedScore, Comparator.reverseOrder());

        for (int i = m - 1; i < boxedScore.length; i += m)
        answer += boxedScore[i] * m;

        return answer;
    }
}