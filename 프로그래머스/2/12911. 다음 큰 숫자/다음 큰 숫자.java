
class Solution {
    public int solution(int n) {
        int numOfOnes = countOnes(n);

        for (int i = n + 1;; i++)
        if (numOfOnes == countOnes(i))  return i;
    }

    private int countOnes(int num)  {
        int result = 0;
        int prev = num;
        while (num > 0) {
            num /= 2;
            result += prev - 2 * num == 0 ? 0 : 1;
            prev = num;
        }

        return result;
    }
}
