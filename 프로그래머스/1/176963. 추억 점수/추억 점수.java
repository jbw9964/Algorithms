import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> yearingMap = new HashMap<>();
        for (int i = 0; i < name.length; i++)
        yearingMap.put(name[i], yearning[i]);

        int[] answer = new int[photo.length];

        for (int i = 0; i < answer.length; i++)
        for (String person : photo[i])
        answer[i] += yearingMap.getOrDefault(person, 0);

        return answer;
    }
}