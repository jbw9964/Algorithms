
class Solution {
    public int solution(int n) {
        // false : prime    |   true : not prime
        boolean[] primeTable = new boolean[n + 1];
        primeTable[0] = primeTable[1] = true;
        
        int answer = 0;

        for (int num = 2; num <= n; num++)   if (!primeTable[num])   {
            for (int i = 2 * num; i <= n; i += num)
            primeTable[i] = true;
            answer++;
        }

        return answer;
    }
}