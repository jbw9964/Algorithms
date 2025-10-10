@SuppressWarnings("DuplicatedCode")
class Solution {

    private static final int[] DISCOUNT = {10, 20, 30, 40};

    private static int[][] users;
    private static int[] emoticons;
    private static int MAX_PLUS_USER_CNT;
    private static int EMOTICON_SALE_PROFIT;

    private static void init(int[][] users, int[] emoticons) {

        Solution.users = new int[users.length][];
        for (int i = 0; i < users.length; i++) {
            Solution.users[i] = users[i].clone();
        }

        Solution.emoticons = emoticons.clone();
    }

    public int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);
        return solve();
    }

    private static int[] solve() {
        dfs(0, new int[emoticons.length]);

        return new int[]{
                MAX_PLUS_USER_CNT, EMOTICON_SALE_PROFIT
        };
    }

    private static void dfs(int index, int[] discountIndices) {

        if (index == emoticons.length) {

            int plusUserCnt = countPlusUsersOn(discountIndices);
            int emoticonSaleProfit = getEmoticonSaleProfitOn(discountIndices);

            if (plusUserCnt > MAX_PLUS_USER_CNT) {
                MAX_PLUS_USER_CNT = plusUserCnt;
                EMOTICON_SALE_PROFIT = emoticonSaleProfit;
            } else if (plusUserCnt == MAX_PLUS_USER_CNT) {
                EMOTICON_SALE_PROFIT = Math.max(EMOTICON_SALE_PROFIT, emoticonSaleProfit);
            }

            return;
        }

        for (int trial = 0; trial < DISCOUNT.length; trial++) {

            int prev = discountIndices[index];

            discountIndices[index] = trial;
            dfs(index + 1, discountIndices);
            discountIndices[index] = prev;

        }
    }

    private static int countPlusUsersOn(int[] discountIndices) {

        if (discountIndices.length != emoticons.length) {
            throw new RuntimeException();
        }

        int len = emoticons.length;

        int count = 0;
        for (int[] user : users) {

            int discountThreshold = user[0];
            int priceThreshold = user[1];

            int buySum = 0;
            for (int i = 0; i < len; i++) {

                int discountRatio = DISCOUNT[discountIndices[i]];

                // 할인이 충분치 않다
                if (discountRatio < discountThreshold) {
                    continue;
                }

                // 이모티콘 산다
                buySum += getDiscountedEmoticonPrice(i, discountRatio);
            }

            // plus 유저로 변한다
            if (buySum >= priceThreshold) {
                count++;
            }
        }

        return count;
    }

    private static int getEmoticonSaleProfitOn(int[] discountIndices) {
        if (discountIndices.length != emoticons.length) {
            throw new RuntimeException();
        }

        int len = emoticons.length;
        int emoticonSaleProfitSum = 0;

        for (int[] user : users) {

            int discountThreshold = user[0];
            int priceThreshold = user[1];

            int buySum = 0;
            for (int i = 0; i < len; i++) {

                int discountRatio = DISCOUNT[discountIndices[i]];

                // 할인이 충분치 않다
                if (discountRatio < discountThreshold) {
                    continue;
                }

                // 이모티콘 산다
                buySum += getDiscountedEmoticonPrice(i, discountRatio);
            }

            // plus 가입은 안한다
            if (buySum < priceThreshold) {
                emoticonSaleProfitSum += buySum;
            }
        }

        return emoticonSaleProfitSum;
    }

    private static int getDiscountedEmoticonPrice(int emoticonIndex, int discountRatio) {
        int rawPrice = emoticons[emoticonIndex];
        return ((100 - discountRatio) * rawPrice) / 100;
    }
}
