import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String s) {

        char[] array = s.toCharArray();
        Map<Character, Integer> letterMap = new HashMap<>();

        int[] answer = new int[array.length];
        for (int i = 0; i < array.length; i++)  {
            char letter = array[i];

            if (letterMap.containsKey(letter))  answer[i] = i - letterMap.get(letter);
            else                                answer[i] = -1;

            letterMap.put(letter, i);
        }

        return answer;
    }
}