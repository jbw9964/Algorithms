import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        if (n > s)      return new int[] {-1};
        
        int[] answer = new int[n];
        int quotient = s / n;
        int mod = s - quotient * n;

        Arrays.fill(answer, quotient);
        
        for (int i = n - 1; i >= 0 && mod-- > 0; i--)
        answer[i]++;
        

        return answer;
    }
}
