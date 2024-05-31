
class Solution {
    public int solution(int[] nums) {
        // false : prime        |   true : not prime
        boolean[] primeTable = new boolean[3_000 + 1];
        primeTable[0] = primeTable[1] = true;
        int sqrt = (int) Math.sqrt(3_000);

        for (int i = 2; i <= sqrt; i++)     if (!primeTable[i])
        for (int j = 2 * i; j <= 3_000; j += i)
        primeTable[j] = true;
        
        int answer = 0;
        int len = nums.length;

        for (int i = 0; i < len - 2; i++)
        for (int j = i + 1; j < len - 1; j++)
        for (int k = j + 1; k < len; k++)
        if (!primeTable[nums[i] + nums[j] + nums[k]])
        answer++;

        return answer;
    }
}