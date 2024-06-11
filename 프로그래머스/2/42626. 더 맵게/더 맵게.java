import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Long> heap = new PriorityQueue<>();
        for (int val : scoville)    heap.add((long) val);

        while (heap.peek() < K && heap.size() > 1)     {
            long lowest = heap.poll();
            long lowest2 = heap.poll();

            long newScoville = lowest + 2 * lowest2;
            heap.add(newScoville);

            answer++;
        }

        return heap.peek() >= K ? answer : -1;
    }
}