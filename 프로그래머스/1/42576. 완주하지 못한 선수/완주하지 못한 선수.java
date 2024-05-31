import java.util.Map;
import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> nameMap = new HashMap<>();

        for (String name : completion)
        nameMap.put(name, nameMap.getOrDefault(name, 0) + 1);
        
        String answer = "";

        for (String name : participant)
        if (!nameMap.containsKey(name) || nameMap.get(name) == 0)   {
            answer = name;
            break;
        }
        else    nameMap.put(name, nameMap.get(name) - 1);
        
        return answer;
    }
}