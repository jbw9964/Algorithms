import java.util.PriorityQueue;

class Solution {
    public String[] solution(String[] strings, int n) {
        PriorityQueue<String> heap = new PriorityQueue<>((prev, current) -> {
            char head = prev.charAt(n);
            char tail = current.charAt(n);
            
            return head - tail != 0 ? head - tail : prev.compareTo(current);
        });
        
        for (String str : strings)
        heap.add(str);
        
        String[] answer = new String[strings.length];
        for (int i = 0; i < answer.length; i++)
        answer[i] = heap.poll();
        
        return answer;
    }
}