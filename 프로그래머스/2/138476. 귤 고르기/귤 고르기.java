import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    private static class Node {
        int fruit, count;
        Node(int fruit, int count)  {
            this.fruit = fruit;
            this.count = count;
        }
    }
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int temp : tangerine)
        countMap.put(temp, countMap.getOrDefault(temp, 0) + 1);

        PriorityQueue<Node> heap = new PriorityQueue<>(
            (prev, current) -> current.count - prev.count
        );

        for (int fruit : countMap.keySet())
        heap.add(new Node(fruit, countMap.get(fruit)));

        int count = 0;
        for (; k > 0 && !heap.isEmpty(); count++)  {
            Node node = heap.poll();
            k -= node.count;
        }

        return count;
    }
}