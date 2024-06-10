import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final Map<String, Integer> dictMap = new HashMap<>();
    
    public int solution(String word) {
        initDict("A");
        initDict("E");
        initDict("I");
        initDict("O");
        initDict("U");

        return dictMap.get(word);
    }

    private static void initDict(String init)  {
        dictMap.put(init, dictMap.size() + 1);

        if (init.length() < 5)  {
            initDict(init + "A");
            initDict(init + "E");
            initDict(init + "I");
            initDict(init + "O");
            initDict(init + "U");
        }
    }
}