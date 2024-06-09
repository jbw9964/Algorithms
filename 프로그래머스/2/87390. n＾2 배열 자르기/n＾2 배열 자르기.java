
class Solution {
    public int[] solution(int n, long left, long right) {
        int lR = (int) (left / n);
        int lC = (int) (left - lR * n);

        int[] answer = new int[(int) (right - left) + 1];
        for (int i = 0; i <= (int) (right - left); i++)  {
            answer[i] = lC <= lR ? lR + 1 : lC + 1;

            lC = lC == n - 1 ? 0 : lC + 1;
            lR = lC == 0 ? lR + 1 : lR;
        }

        return answer;
    }
}