import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;

class Solution {
    private static int MAX_COUNT = Integer.MIN_VALUE;
    private static int[] MAX_SELECTION = null;

    public int[] solution(int[][] dice) {
        int N = dice.length;
        int R = N / 2;

        Queue<int[]>[] indexCombinations = getPairIndexComb(N, R);
        Queue<int[]> indexCombA = indexCombinations[0];
        Queue<int[]> indexCombB = indexCombinations[1];

        while (!indexCombA.isEmpty())   {
            int[] selectionA = indexCombA.poll();
            int[] selectionB = indexCombB.poll();

            Queue<Integer> A = getPermute(dice, selectionA);
            Queue<Integer> B = getPermute(dice, selectionB);

            int count = 0;
            while (!A.isEmpty() && !B.isEmpty())    {
                Integer peek;
                while ((peek = B.peek()) != null && A.peek() <= peek)
                B.poll();

                count += B.size();
                A.poll();
            }

            if (MAX_COUNT < count)  {
                MAX_COUNT = count;
                MAX_SELECTION = selectionA;
            }
        }
        
        int[] answer = new int[R];
        for (int i = 0; i < R; i++)
        answer[i] = MAX_SELECTION[i] + 1;

        Arrays.sort(answer);

        return answer;
    }

    @SuppressWarnings("unchecked")
    private static Queue<int[]>[] getPairIndexComb(int n, int r)    {
        Queue<int[]>[] result = new Queue[] {
            new LinkedList<>(),
            new LinkedList<>()
        };
        
        storePairIndexComb(
            result[0], result[1], new boolean[n], 
            n, r, 0, 0
        );

        return result;
    }
    private static void storePairIndexComb(
        Queue<int[]> dst1, Queue<int[]> dst2, boolean[] visitTable,
        int n, int r, int index, int len
    )
    {
        if (len == r)   {
            int cursor1 = 0;
            int cursor2 = 0;

            int[] indices1 = new int[r];
            int[] indices2 = new int[r];
            for (int i = 0; i < visitTable.length; i++) {
                if (visitTable[i])  indices1[cursor1++] = i;
                else                indices2[cursor2++] = i;
            }
            
            dst1.add(indices1);
            dst2.add(indices2);

            return;
        }

        for (int i = index; i < n; i++) {
            visitTable[i] = true;
            storePairIndexComb(
                dst1, dst2, visitTable, 
                n, r, i + 1, len + 1
            );
            visitTable[i] = false;
        }

    }

    @SuppressWarnings("unchecked")
    private static Queue<Integer> getPermute(int[][] dice, int[] selection)  {
        List<Integer> result = getPermute(new LinkedList<>(), dice, selection, 0, 0);
        Collections.sort(result, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        return (Queue<Integer>) result;
    }
    private static List<Integer> getPermute(
        List<Integer> list, int[][] dice, 
        int[] selection, int index, int value
    ) {

        if (index == selection.length)  {
            list.add(value);
            return null;
        }

        int[] availableValues = dice[selection[index]];
        for (int i = 0; i < availableValues.length; i++)    {
            int add = availableValues[i];
            getPermute(list, dice, selection, index + 1, value + add);
        }

        return list;
    }
}