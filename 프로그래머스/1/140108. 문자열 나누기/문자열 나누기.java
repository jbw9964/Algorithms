
class Solution {
    public int solution(String s) {
        
        char[] array = s.toCharArray();
        int index = 0;

        int answer = 0;
        while (index < array.length)  {
            char currentLetter = array[index];
            int countCurrent = 1;
            int countElse = 0;
            
            while (++index < array.length && countCurrent != countElse)
            if (currentLetter == array[index])  countCurrent++;
            else                                countElse++;

            answer++;
        }

        return answer;
    }
}