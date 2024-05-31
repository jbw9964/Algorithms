import java.util.LinkedList;
import java.util.List;

public class Solution {
    public int[] solution(int[] arr) {
        List<Integer> uniqueNumbers = new LinkedList<>();

        int prev = -1;
        for (int num : arr)
        if (prev != num) {
            uniqueNumbers.add(num);
            prev = num;
        }
        
        return uniqueNumbers.stream().mapToInt(val -> val).toArray();
    }
}