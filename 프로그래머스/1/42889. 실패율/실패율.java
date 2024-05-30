import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {
    private static class Node {
        double failureRate;
        int stageIndex;
        Node(double rate, int index)    {
            failureRate = rate;
            stageIndex = index;
        }

        @Override
        public String toString() {
            return String.format("[%f, %d]", failureRate, stageIndex);
        }
    }

    public int[] solution(int N, int[] stages) {
        PriorityQueue<Integer> rateCalcHeap = new PriorityQueue<>();
        for (int stage : stages)    rateCalcHeap.add(stage);
        
        PriorityQueue<Node> sortViaRateHeap = new PriorityQueue<>((prev, curret) -> {
            int comp = Double.compare(curret.failureRate, prev.failureRate);
            return comp == 0 ? prev.stageIndex - curret.stageIndex : comp;
        });

        Set<Integer> unScoredStages = new HashSet<>();
        for (int i = 1; i <= N; i++)    unScoredStages.add(i);

        while (!rateCalcHeap.isEmpty())     {
            int total = rateCalcHeap.size();
            int stage = rateCalcHeap.poll();

            if (stage > N)      break;
            
            int count = 1;

            while (!rateCalcHeap.isEmpty() && rateCalcHeap.peek() == stage)   {
                count++;
                rateCalcHeap.poll();
            }

            double failureRate = (double) count / total;

            sortViaRateHeap.add(new Node(failureRate, stage));
            unScoredStages.remove(stage);
        }
        
        for (int stage : unScoredStages)
        sortViaRateHeap.add(new Node(0, stage));

        int[] answer = new int[sortViaRateHeap.size()];
        
        for (int i = 0; i < answer.length; i++)
        answer[i] = sortViaRateHeap.poll().stageIndex;

        return answer;
    }
}