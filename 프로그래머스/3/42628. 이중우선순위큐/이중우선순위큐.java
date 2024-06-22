import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

class Solution {
    public int[] solution(String[] operations) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        Map<Integer, Integer> numberCountMap = new HashMap<>();
        
        for (int i = 0; i < operations.length; i++)     {
            String[] command = operations[i].split(" ");
            int value = Integer.parseInt(command[1]);

            if (command[0].equals("I"))     {
                treeSet.add(value);
                numberCountMap.put(value, numberCountMap.getOrDefault(value, 0) + 1);
                continue;
            }
            else if (treeSet.isEmpty())     continue;
            

            value = value == 1 ? treeSet.pollLast() : treeSet.pollFirst();

            if (numberCountMap.get(value) > 1)  treeSet.add(value);
            numberCountMap.put(value, numberCountMap.get(value) - 1);
        }
        
        if (treeSet.isEmpty())      return new int[] {0, 0};

        int maxima = treeSet.pollLast();
        Integer minima = treeSet.pollFirst();

        return new int[] {maxima, minima == null ? maxima : minima};
    }
}