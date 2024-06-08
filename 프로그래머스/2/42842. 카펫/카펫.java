import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int sum = brown + yellow;
        int[] factors = getFactors(sum);

        for (int row : factors)     {
            int col = sum / row;

            if (brown == 2 * (row + col - 2))   {
                answer[0] = col;
                answer[1] = row;
            }
        }

        return answer;
    }

    private int[] getFactors(int num)   {
        List<Integer> list = new LinkedList<>();
        int sqrt = (int) Math.sqrt(num);
        
        for (int n = 2; n <= sqrt; n++)
        if (num % n == 0)   list.add(n);

        return list.stream().mapToInt(val -> val).toArray();
    }
}
