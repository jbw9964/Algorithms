import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    private static final int MUL = 65536;

    public int solution(String str1, String str2) {
        HashMap<String, Integer> setAMap = new HashMap<>();
        HashMap<String, Integer> setBMap = new HashMap<>();

        preprocess(str1, setAMap);
        preprocess(str2, setBMap);

        Set<String> keySetA = new HashSet<>(setAMap.keySet());
        Set<String> keySetB = new HashSet<>(setBMap.keySet());
        Set<String> tempSet = new HashSet<>(setAMap.keySet());

        tempSet.retainAll(keySetB);
        Set<String> intersect = new HashSet<>(tempSet);

        tempSet.addAll(keySetA);
        tempSet.addAll(keySetB);
        
        Set<String> union = new HashSet<>(tempSet);

        int numOfUnions = 0;
        for (String key : union)    {
            int countA = setAMap.getOrDefault(key, 0);
            int countB = setBMap.getOrDefault(key, 0);
            numOfUnions += (int) Math.max(countA, countB);
        }

        int numOfIntersect = 0;
        for (String key : intersect)    {
            int countA = setAMap.get(key);
            int countB = setBMap.get(key);
            numOfIntersect += (int) Math.min(countA, countB);
        }

        return numOfIntersect == 0 && numOfUnions == 0 ? MUL : (int) ((double) numOfIntersect / numOfUnions * MUL);
    }

    private void preprocess(String str, Map<String, Integer> map)   {
        char[] letters = str.toCharArray();

        for (int i = 0; i < letters.length - 1; i++)    {
            char letter1 = Character.toUpperCase(letters[i]);
            char letter2 = Character.toUpperCase(letters[i + 1]);

            if (!Character.isAlphabetic(letter1) || !Character.isAlphabetic(letter2))
            continue;

            String concate = String.valueOf(letter1) + String.valueOf(letter2);
            map.put(concate, map.getOrDefault(concate, 0) + 1);
        }

    }
}