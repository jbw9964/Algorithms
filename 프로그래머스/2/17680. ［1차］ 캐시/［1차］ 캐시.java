import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Map<String, Integer> cacheMap = new HashMap<>();
        PriorityQueue<String> cacheHeap = new PriorityQueue<>(
            (prev, current) -> cacheMap.getOrDefault(prev, 0) - cacheMap.getOrDefault(current, 0)
        );

        int answer = 0;

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toLowerCase();

            answer += cacheHeap.contains(city) ? 1 : 5;

            cacheMap.put(city, i);

            if (cacheHeap.contains(city))   cacheHeap.remove(city);
            cacheHeap.add(city);

            while (cacheHeap.size() > cacheSize)
            cacheHeap.poll();
        }
        
        return answer;
    }
}