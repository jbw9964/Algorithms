import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> map = new HashMap<>();

        for (String keys : keymap)  {
            char[] key = keys.toCharArray();

            for (int i = 0; i < key.length; i++)    {
                if (!map.containsKey(key[i]) || map.get(key[i]) > i + 1)
                map.put(key[i], i + 1);
            }
        }
        
        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++)    {
            for (char letter : targets[i].toCharArray())    {
                if (!map.containsKey(letter))   {
                    answer[i] = -1;
                    break;
                }

                answer[i] += map.get(letter);
            }
        }

        return answer;
    }
}