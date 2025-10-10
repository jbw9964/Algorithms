
@SuppressWarnings("DuplicatedCode")
class Solution {

    private static int CAP, N;
    private static int[] DELIVERIES, PICKUPS;

    private static void init(int cap, int n, int[] deliveries, int[] pickups) {
        CAP = cap;
        N = n;
        DELIVERIES = deliveries.clone();
        PICKUPS = pickups.clone();
    }

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        init(cap, n, deliveries, pickups);

        int pivot = n - 1;
        long answer = 0;

        while (true) {

            while (
                    pivot >= 0 &&
                    DELIVERIES[pivot] == PICKUPS[pivot] &&
                    DELIVERIES[pivot] == 0
            ) {
                pivot--;
            }

            if (pivot == -1)    {
                break;
            }

            deliverFrom(pivot);
            pickupFrom(pivot);
            answer += 2L * (pivot + 1);
        }

        return answer;
    }

    private static void deliverFrom(int pivot) {

        int available = CAP;
        while (available > 0 && pivot >= 0) {

            if (DELIVERIES[pivot] == 0) {
                pivot--;
                continue;
            }

            int remains = DELIVERIES[pivot];
            if (available > remains) {
                DELIVERIES[pivot--] = 0;
                available -= remains;
            } else {
                DELIVERIES[pivot] -= available;
                break;
            }
        }
    }

    private static void pickupFrom(int pivot) {

        int available = CAP;
        while (available > 0 && pivot >= 0) {
            if (PICKUPS[pivot] == 0) {
                pivot--;
                continue;
            }

            int remains = PICKUPS[pivot];
            if (available > remains) {
                PICKUPS[pivot--] = 0;
                available -= remains;
            } else {
                PICKUPS[pivot] -= available;
                break;
            }
        }
    }
}
