import java.util.*;


class Solution {

    private static final int PW_LEN = 5;

    private Info[] initInfos(int[][] q, int[] ans)  {
        Info[] infos = new Info[q.length];

        for (int i = 0; i < q.length; i++) {
            infos[i] = new Info(q[i], ans[i]);
        }

        return infos;
    }

    private List<Set<Integer>> initEveryCasesWithDFS(
            int n, int index, int prevNum, List<Set<Integer>> dst, int[] tmp
    ) {

        if (index >= tmp.length) {

            Set<Integer> newSet = new HashSet<>();
            for (int val : tmp) {
                newSet.add(val);
            }
            dst.add(newSet);

            return dst;
        }

        else if (prevNum >= n)  {
            return dst;
        }

        int restore = tmp[index];

        for (int trial = prevNum + 1; trial <= n; trial++) {
            tmp[index] = trial;
            initEveryCasesWithDFS(
                    n, index + 1, trial, dst, tmp
            );
            tmp[index] = restore;
        }

        return dst;
    }

    public int solution(int n, int[][] q, int[] ans) {

        List<Set<Integer>> everyPossibleCases = initEveryCasesWithDFS(
                n, 0, 0, new ArrayList<>(), new int[PW_LEN]
        );
        Info[] infos = initInfos(q, ans);

        int answer = 0;

        LOOP:
        for (Set<Integer> single : everyPossibleCases) {

            for (Info info : infos) {
                if (!info.doesMatch(single))    {
                    continue LOOP;
                }
            }

            answer++;
        }

        return answer;
    }
}

class Info  {
    private final int[] numbers;
    private final int numOfValid;

    public Info(int[] numbers, int numOfValid) {
        this.numbers = numbers;
        this.numOfValid = numOfValid;
    }

    public boolean doesMatch(Set<Integer> numbers)  {
        int cnt = 0;
        for (int number : this.numbers) {
            if (numbers.contains(number))   {
                cnt++;
            }
        }
        return cnt == this.numOfValid;
    }
}