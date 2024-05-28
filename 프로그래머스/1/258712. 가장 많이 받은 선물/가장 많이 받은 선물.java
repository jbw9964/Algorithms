
import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        
        HashMap<String, Integer> friendIndex = new HashMap<>();
        
        for (int i = 0; i < friends.length; i++)
        friendIndex.put(friends[i], i);
        
        int[][] giftExchangeTable = new int[friends.length][friends.length];
        HashMap<String, Integer> giverHashMap = new HashMap<>();
        HashMap<String, Integer> takerHashMap = new HashMap<>();

        for (String exchange : gifts)   {
            String giver = exchange.split(" ")[0];
            String taker = exchange.split(" ")[1];

            giftExchangeTable[friendIndex.get(giver)][friendIndex.get(taker)]++;
            giverHashMap.put(giver, giverHashMap.getOrDefault(giver, 0) + 1);
            takerHashMap.put(taker, takerHashMap.getOrDefault(taker, 0) + 1);
        }

        HashMap<String, Integer> nextMonthTakerHashMap = new HashMap<>();
        for (int i = 0; i < friends.length - 1; i++)    {
            String giver = friends[i];

            for (int j = i + 1; j < friends.length; j++)    {
                String taker = friends[j];

                int giverIndex = friendIndex.get(giver);
                int takerIndex = friendIndex.get(taker);

                int giverValue = giftExchangeTable[giverIndex][takerIndex];
                int takerValue = giftExchangeTable[takerIndex][giverIndex];

                if (giverValue == takerValue) {
                    int giverCredit = giverHashMap.getOrDefault(giver, 0) - takerHashMap.getOrDefault(giver, 0);
                    int takerCredit = giverHashMap.getOrDefault(taker, 0) - takerHashMap.getOrDefault(taker, 0);

                    if (giverCredit == takerCredit)     continue;

                    String person = giverCredit > takerCredit ? giver : taker;
                    nextMonthTakerHashMap.put(person, nextMonthTakerHashMap.getOrDefault(person, 0) + 1);
                }
                else    {
                    String person = giverValue > takerValue ? giver : taker;
                    nextMonthTakerHashMap.put(person, nextMonthTakerHashMap.getOrDefault(person, 0) + 1);
                }
            }
        }
        
        int answer = 0;
        for (Integer value : nextMonthTakerHashMap.values())
        answer = answer < value ? value : answer;

        return answer;
    }
}