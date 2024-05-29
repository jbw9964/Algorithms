import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String solution(int[] food) {
        Deque<Integer> head = new LinkedList<>();
        Deque<Integer> tail = new LinkedList<>();
        
        for (int i = 0; i < food.length; i++)   {
            int foodNum = food[i];

            for (int j = 0; j < foodNum / 2; j++)   {
                head.addLast(i);
                tail.addFirst(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        
        while (!head.isEmpty()) sb.append(head.pollFirst());
        sb.append("0");
        while (!tail.isEmpty()) sb.append(tail.pollFirst());
        
        return sb.toString();
    }
}