import java.util.*;
import java.util.stream.*;

class Solution {

    private static int N;
    private static Map<String, Integer> indexMap;
    private static int[][] giftTable;

    private static void init(String[] friends, String[] gifts) {
        N = friends.length;
        indexMap = new HashMap<>();

        for (int i = 0; i < N; i++) {
            indexMap.put(friends[i], i);
        }

        giftTable = new int[N][N];
        for (String gift : gifts) {

            String[] tmp = gift.split(" ");
            String from = tmp[0];
            String to = tmp[1];

            int fI = indexMap.get(from);
            int tI = indexMap.get(to);

            giftTable[fI][tI]++;
        }
    }

    public int solution(String[] friends, String[] gifts) {

        init(friends, gifts);

        int[] answer = new int[N];

        for (int i = 0; i < N - 1; i++) {
            String fI = friends[i];

            for (int j = i + 1; j < N; j++) {
                String fJ = friends[j];

                int giveI = giftTable[i][j];
                int giveJ = giftTable[j][i];

                if (giveI > giveJ) {
                    answer[i]++;
                } else if (giveJ > giveI) {
                    answer[j]++;
                } else {

                    int scoreI = getGiftScore(fI);
                    int scoreJ = getGiftScore(fJ);

                    if (scoreI > scoreJ) {
                        answer[i]++;
                    } else if (scoreJ > scoreI) {
                        answer[j]++;
                    }
                }
            }
        }

        return Arrays.stream(answer).max().orElseThrow(RuntimeException::new);
    }

    private static int getGiftScore(String name) {
        int give = countGive(name);
        int get = countGet(name);
        return give - get;
    }

    private static int countGive(String from) {
        int fI = indexMap.get(from);
        return Arrays.stream(giftTable[fI]).sum();
    }

    private static int countGet(String to) {
        int tI = indexMap.get(to);

        int sum = 0;
        for (int j = 0; j < N; j++) {
            sum += giftTable[j][tI];
        }

        return sum;
    }
}