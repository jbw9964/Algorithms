import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Solution {

    @SuppressWarnings("unchecked")
    private static final List<int[]>[] requestArray = new List[5];

    private static final Map<List<int[]>, Integer[]> cachedEstimatedTimes
            = new HashMap<>();

    private void init(int numOfCouncilType, int[][] reqs) {

        for (int i = 0; i < numOfCouncilType; i++) {
            requestArray[i] = new LinkedList<>();
        }

        // 각 상담 타입별 요청 분류, List 에 저장
        // r[2] : 상담 타입
        for (int[] req : reqs) {
            int councilType = req[2];
            requestArray[councilType - 1].add(req);
        }

        // 중복 계산 빠르게 하려고 cache 개체 추가

        for (List<int[]> req : requestArray)    {
            cachedEstimatedTimes.put(req, new Integer[20 + 1]);
        }
    }

    public int solution(int k, int n, int[][] reqs) {
        init(k, reqs);

        return switch (k) {
            case 1 -> greedySearch(n, 1);
            case 2 -> greedySearch(n, 1, 1);
            case 3 -> greedySearch(n, 1, 1, 1);
            case 4 -> greedySearch(n, 1, 1, 1, 1);
            case 5 -> greedySearch(n, 1, 1, 1, 1, 1);
            default -> throw new IllegalArgumentException();
        };
    }

    private int greedySearch(int n, int... allocated) {

        // 할당된 상담원 수
        int currentAllocated = allocated.length;

        while (currentAllocated < n) {

            // 상담원을 추가 할당 시, 가장 이득인 상담 타입 index
            int optimalIndex = -1;
            int maximumDiff = Integer.MIN_VALUE;

            for (int i = 0; i < allocated.length; i++) {

                // 현 할당된 상담원으로 waiting 계산
                int currentWaiting = estimateWaiting(allocated[i], requestArray[i]);

                // 1 명 추가했을 때 waiting 계산
                int next = estimateWaiting(allocated[i] + 1, requestArray[i]);

                int diff = currentWaiting - next;

                if (diff > maximumDiff) {
                    optimalIndex = i;
                    maximumDiff = diff;
                }
            }

            // 가장 이득인 상담원 +1
            allocated[optimalIndex]++;

            currentAllocated++;
        }

        // 할당할 수 있는 모든 상담원 배치 완료
        // 마지막 계산
        return IntStream.range(0, allocated.length)
                .map(i -> estimateWaiting(allocated[i], requestArray[i]))
                .sum();
    }

    // 주어진 상담원 수와 상담 요청들로 waiting time 계산
    private int estimateWaiting(int councilNum, final List<int[]> requests) {

        assert councilNum >= 1;

        // 이전 캐쉬된 계산 있으면 return
        if (isCached(councilNum, requests)) {
            return getCachedTime(councilNum, requests);
        }

        int totalWaiting = 0;

        // r[0] : 상담 시작한 시간
        // r[1] : 상담 duration
        // r[2] : 상담 타입
        // 가장 빨리 끝나는 순으로 힙정렬 정의
        PriorityQueue<int[]> inProcess = new PriorityQueue<>(
                Comparator.comparingInt(r -> (r[0] + r[1]))
        );

        // req[0] : 상담을 원하는 시간
        // req[1] : 상담 duration
        // req[2] : 상담 타입
        for (int[] req : requests) {

            // 지금 상담할 수 없음. 가장 빨리 끝나는 사람 끝나고 진행해야 함.
            if (inProcess.size() >= councilNum) {

                // 가장 빨리 끝나는 request
                int[] fastest = inProcess.poll();

                // 가장 빨리 끝나는 시간
                int fastestEnd = fastest[0] + fastest[1];

                // 가장 빨리 끝나는 사람이 요청한 시간보다 더 늦게 끝남.
                // 요청 시간 & 끝나는 시간만큼 기다리니까
                // req[0], 전체 기다림 +=
                if (fastestEnd > req[0]) {

                    // 디버깅 해보니 배열 값 자체를 바꿔서 다음 search 에도 영향 줌.
                    // 귀찮아서 clone
                    req = req.clone();

                    int waiting = fastestEnd - req[0];
                    req[0] += waiting;
                    totalWaiting += waiting;
                }
            }

            // 상담 들어감.
            inProcess.add(req);
        }

        // (상담요청, 할당된 상담원 수) 로 waiting time cache
        recordCache(totalWaiting, councilNum, requests);

        return totalWaiting;
    }

    private boolean isCached(int councilNum, final List<int[]> requests) {
        return cachedEstimatedTimes.get(requests)[councilNum] != null;
    }

    private int getCachedTime(int councilNum, final List<int[]> requests) {
        return cachedEstimatedTimes.get(requests)[councilNum];
    }

    private void recordCache(int time, int councilNum, final List<int[]> requests) {
        cachedEstimatedTimes.get(requests)[councilNum] = time;
    }
}
