import java.util.*;

class Solution {
    public long solution(int n, int[] works) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(
                Collections.reverseOrder()
        );

        for (int work : works) {
            pq.add(work);
        }

        while (!pq.isEmpty() && n-- > 0)    {
            int next = pq.poll() - 1;
            if (next > 0)   {
                pq.add(next);
            }
        }

        return pq.stream().mapToLong(r -> r * r).sum();
    }
}
