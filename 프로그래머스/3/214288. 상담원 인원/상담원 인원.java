import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class Solution {

    private static int MINIMUM_WAITING = Integer.MAX_VALUE;

    @SuppressWarnings("unchecked")
    private static final List<int[]>[] requestArray = new List[5];

    private static final Map<List<int[]>, Integer[]> watingTimeCacheMap
            = new HashMap<>();

    private void init(int numOfCouncilType, int[][] reqs) {

        // 배열 초기화
        for (int i = 0; i < numOfCouncilType; i++) {
            requestArray[i] = new LinkedList<>();
        }

        // 각 상담 타입별 요청 분류, List 에 저장
        // r[2] : 상담 타입
        for (int[] req : reqs) {
            int councilType = req[2];
            requestArray[councilType - 1].add(req);
        }

        // 기다리는 시간 cache 하는 객체 만듬
        // (요청, 할당된 상담원 수) 에 따른 cacheMap
        for (List<int[]> req : requestArray) {
            // cacheMap 초기화
            watingTimeCacheMap.put(req, new Integer[20]);
        }
    }

    public int solution(int k, int n, int[][] reqs) {
        // 요청 분류, cache map 초기화
        init(k, reqs);

        // 완전 탐색 진행
        compSearch(n, 0, new int[k]);

        return MINIMUM_WAITING;
    }

    // allocated : 타입 별 할당된 상담원 명수
    private void compSearch(int n, int index, int[] allocated) {

        // 마지막 index 초과
        if (index == allocated.length) {

            // 상담원 최대 할당 완료
            if (Arrays.stream(allocated).sum() >= n) {

                // 할당된 상담원으로 기다리는 시간 estimate
                int totalWaiting = IntStream.range(0, allocated.length)
                        .map(i -> estimateWaiting(allocated[i], requestArray[i]))
                        .sum();

                MINIMUM_WAITING = Math.min(MINIMUM_WAITING, totalWaiting);
            }

            // 아직 상담원 추가 할당 가능

            return;
        }

        int prev = allocated[index];

        // 지금 더 할당할 수 있느 상담원 수
        int allocatableCouncils = n     // 전체 상담원 수
                - (index == 0 ? 0 : Arrays.stream(allocated).limit(index).sum())
                // 앞서 할당된 상담원 수 (-)
                - (allocated.length - index - 1);   // 이후 상담 타입 별 1 명씩 필요하므로 그만큼 (-)

        for (int councilNum = 1; councilNum <= allocatableCouncils; councilNum++) {

            // 상담원 할당하기
            allocated[index] = councilNum;

            // 다음 타입 상담원 할당해보기
            // 더 추가 할당될 수 있으면 estimateWaiting 호출 안됨
            compSearch(n, index + 1, allocated);
        }

        // 할당되었던 상담원 복원
        allocated[index] = prev;
    }

    // 한 상담 type 의 기다리는 시간 측정
    private int estimateWaiting(int councilNum, final List<int[]> requests) {

        if (requests == null) { // 혹시 몰라 추가
            return 0;
        }

        assert councilNum >= 1;
        int totalWaiting = 0;

        // 이전 cache 된 값이 있으면 바로 return
        if (isCached(requests, councilNum)) {
            return getCachedWaitingTime(requests, councilNum);
        }

        // r[0] : 상담 시작한 시간
        // r[1] : 상담 duration
        // r[2] : 상담 타입
        // 가장 빨리 끝나는 순으로 정렬
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
                    req = req.clone();  // 디버깅 해보니 배열 값 자체를 바꿔서 다음 search 에도 영향 줌.
                    // 귀찮아서 clone

                    int waiting = fastestEnd - req[0];
                    req[0] += waiting;
                    totalWaiting += waiting;
                }
            }

            // 상담 들어감.
            inProcess.add(req);
        }

        // 계산 값 cache 저장
        cacheCalculation(totalWaiting, requests, councilNum);

        return totalWaiting;
    }

    private boolean isCached(final List<int[]> requests, final int councilNum) {
        return watingTimeCacheMap.get(requests)[councilNum] != null;
    }

    private int getCachedWaitingTime(final List<int[]> requests, final int councilNum) {
        return watingTimeCacheMap.get(requests)[councilNum];
    }

    private void cacheCalculation(final int waiting, final List<int[]> requests,
            final int councilNum) {
        watingTimeCacheMap.get(requests)[councilNum] = waiting;
    }
}

/*
 *  상담원 최대 20 명
 *  [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o] [o]
 *
 * 위 상담원을 최대 5 상담 타입으로 분류해야 함.
 *
 * 즉, 상담원 사이 빈 공간 19 곳 중 구분자 || 를 4 곳에 선택해 분류하면 됨. (아래처럼)
 * 어떤 상담원이 어느 타입 상담을 배정받는지 안 따져도 되기 때문에 가능
 *
 * [o] [o] [o] [o]  |   [o] [o] [o] |   [o] [o] [o] [o] |   [o] [o] [o] [o] [o] [o] |   [o] [o] [o]
 *
 * --> 19C4 = (19 * 18 * 17 * 16) / 4! = 3876
 * --> 3876 * (300 *  20log20) <= 3.3천만, 최악의 최악의 최악이므로 시간초과 안될듯?
 */