import java.util.HashSet;
import java.util.Set;

class Solution {
    private static Set<Integer> numberSet = new HashSet<>();
    private static int MAXIMA = Integer.MIN_VALUE;

    public int solution(String numbers) {
        char[] array = numbers.toCharArray();
        
        for (int len = 1; len <= array.length; len++)   {
            boolean[] visitTable = new boolean[array.length];
            recordAllCombinations(array, visitTable, new StringBuilder(), len, 0);
        }

        // false : prime        |       true : not prime
        boolean[] primeTable = new boolean[MAXIMA + 1];
        primeTable[0] = primeTable[1] = true;
        int sqrt = (int) Math.sqrt(MAXIMA);

        for (int n = 2; n <= sqrt; n++)     if (!primeTable[n])
        for (int mul = 2 * n; mul <= MAXIMA; mul += n)
        primeTable[mul] = true;

        int answer = 0;
        for (int num : numberSet)
        answer += primeTable[num] ? 0 : 1;

        return answer;
    }

    private void recordAllCombinations(
        char[] digits, boolean[] visitTable, 
        StringBuilder number, int maxLen, int len
    )    {

        if (maxLen <= len)      {
            int num = Integer.parseInt(number.toString());
            MAXIMA = Math.max(MAXIMA, num);
            numberSet.add(num);
            return;
        }

        for (int i = 0; i < digits.length; i++)     {
            if (visitTable[i])                      continue;

            char digit = digits[i];
            visitTable[i] = true;
            number.append(digit);

            recordAllCombinations(digits, visitTable, number, maxLen, len + 1);
            
            visitTable[i] = false;
            number.deleteCharAt(len);
        }
    }
}