import java.util.PriorityQueue;

class Solution {
    public int[] solution(int k, int[] score) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (a, b) -> a - b
        );

        int[] answer = new int[score.length];

        for (int i = 0; i < score.length; i++)  {
            int value = score[i];
            if (heap.size() < k)            heap.add(value);
            else if (heap.peek() < value)   {
                heap.poll();
                heap.add(value);
            }

            answer[i] = heap.peek();
        }

        return answer;
    }
}