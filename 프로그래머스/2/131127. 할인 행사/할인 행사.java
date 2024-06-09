import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> discountMap = new HashMap<>();
        for (int i = 0; i < want.length; i++)
        discountMap.put(want[i], number[i]);

        int answer = 0;

        for (int i = 0; i <= discount.length - 10; i++)  {
            Map<String, Integer> countMap = new HashMap<>();
            countMap.putAll(discountMap);

            for (int j = i; j < i + 10; j++)    {
                String item = discount[j];

                if (!countMap.containsKey(item))    continue;
                
                int count = countMap.get(item);

                if (count <= 1)     countMap.remove(item);
                else                countMap.put(item, count - 1);
            }

            answer += countMap.size() == 0 ? 1 : 0;
        }
        
        return answer;
    }
}