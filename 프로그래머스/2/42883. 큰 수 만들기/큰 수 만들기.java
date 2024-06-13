
class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder().append(number);
        
        int prev = 0;
        while (k-- > 0 && prev < answer.length())     {
            int index = prev;

            while (index + 1 < answer.length() 
                && answer.charAt(index + 1) <= answer.charAt(index))
            index++;

            answer.deleteCharAt(index);

            if (answer.charAt(prev) == '9')     prev++;
        }
        
        return answer.toString().substring(0, answer.length() - (k < 0 ? 0 : k + 1));
    }
}