import java.util.*;

class Solution {

    private static int N;
    private static int TARGET;

    public int solution(int coin, int[] cards) {

        N = cards.length;
        TARGET = N + 1;

        int baseIndex = N / 3;
        Arrays.sort(cards, 0, baseIndex);

        int combinationCount = 0;
        for (int i : getCombinationIndices(cards, 0, baseIndex)) {
            cards[i] = 0;
            combinationCount++;
        }
        combinationCount /= 2;

        int round = 0;
        int threshold;
        LOOP:
        while ((threshold = baseIndex + 2 * round) <= N) {

            if (combinationCount >= round) {
                round++;
                continue LOOP;
            }

            Arrays.sort(cards, baseIndex, threshold);

            // find single case
            for (int i = 0; i < baseIndex; i++) {
                int target = TARGET - cards[i];

                int find = Arrays.binarySearch(cards, baseIndex, threshold, target);
                if (find >= 0 && coin > 0) {
                    cards[find] = 0;
                    combinationCount++;
                    coin--;
                    continue LOOP;
                }
            }

            // find double case
            List<Integer> combinations = getCombinationIndices(cards, baseIndex, threshold);

            // no further round available
            if (combinations.isEmpty() || coin < 2) {
                break LOOP;
            }

            int i1 = combinations.get(0);
            int i2 = combinations.get(1);

            cards[i1] = cards[i2] = 0;
            combinationCount++;
            coin -= 2;
        }

        return round;
    }

    private static List<Integer> getCombinationIndices(
            int[] arr, int fromInclusive, int toExclusive
    ) {

        List<Integer> result = new ArrayList<>();
        int head = fromInclusive;
        int tail = toExclusive - 1;

        while (head < tail) {

            int sum = arr[head] + arr[tail];

            if (sum > TARGET) {
                tail--;
            } else if (sum < TARGET) {
                head++;
            } else {
                result.add(head);
                result.add(tail);
                head++;
                tail--;
            }
        }

        return result;
    }
}
