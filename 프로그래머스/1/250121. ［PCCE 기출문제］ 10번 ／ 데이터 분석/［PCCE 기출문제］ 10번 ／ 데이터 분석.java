
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        Map<String, Integer> indexMap = new HashMap<>();
        indexMap.put("code", 0);
        indexMap.put("date", 1);
        indexMap.put("maximum", 2);
        indexMap.put("remain", 3);

        PriorityQueue<int[]> queue = new PriorityQueue<>(
            (a, b) -> a[indexMap.get(sort_by)] - b[indexMap.get(sort_by)]
        );

        for (int[] row : data)  {
            int extIndex = indexMap.get(ext);
            int value = row[extIndex];

            if (value < val_ext)
            queue.add(row);
        }

        List<int[]> list = new ArrayList<>(queue.size());
        while (!queue.isEmpty())
        list.add(queue.poll());

        return list.toArray(new int[0][]);
    }
}