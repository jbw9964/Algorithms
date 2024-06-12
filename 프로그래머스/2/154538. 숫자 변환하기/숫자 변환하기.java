import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int x, int y, int n) {
        int[] countTable = new int[y + 1];
        Queue<Integer> numbers = new LinkedList<>();
        numbers.add(x);

        while (!numbers.isEmpty())      {
            int num = numbers.poll();
            int count = countTable[num];

            for (int newNum : new int[]{
                num + n, 2 * num, 3 * num
            })
            if (newNum <= y && countTable[newNum] == 0)    {
                countTable[newNum] = count + 1;
                numbers.add(newNum);
            }
        }

        return countTable[y] == 0 && x != y ? -1 : countTable[y];
    }
}