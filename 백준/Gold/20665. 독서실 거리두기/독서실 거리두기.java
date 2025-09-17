import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, T, P;
    private static PriorityQueue<Info> priorityQueue;
    private static TreeSet<Integer> occupancyTree;
    private static int[] occupancyRecord;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;

        priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            String start = st.nextToken();
            String end = st.nextToken();

            int diff = getTimeDiff(start, end);

            if (diff != 0) {
                priorityQueue.add(new Info(i, diff, Type.IN, start));
                priorityQueue.add(new Info(i, diff, Type.OUT, end));
            }
        }

        occupancyTree = new TreeSet<>();
        occupancyRecord = new int[T];
        Arrays.fill(occupancyRecord, -1);
    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;
        String start = "0900";

        while (!priorityQueue.isEmpty()) {

            Info info = priorityQueue.poll();
            int index = info.index;
            String time = info.time;

            if (info.type == Type.IN) {
                int seatToOccupy = inspectSeatIndex();

                if (seatToOccupy == P) {
                    answer += getTimeDiff(start, time);
                }

                occupancyTree.add(seatToOccupy);
                occupancyRecord[index] = seatToOccupy;
            } else {

                int removalSeat = occupancyRecord[index];

                if (removalSeat == P) {
                    start = time;
                }

                occupancyTree.remove(removalSeat);
                occupancyRecord[index] = -1;
            }
        }

        answer += getTimeDiff(start, "2100");

        System.out.println(answer);
    }

    private static int inspectSeatIndex() {
        int index = -1;
        int maxDist = Integer.MIN_VALUE;

        for (int seat = 0; seat < N; seat++) {

            if (occupancyTree.contains(seat)) {
                continue;
            }

            Integer lower = occupancyTree.lower(seat);
            Integer higher = occupancyTree.higher(seat);

            int dist = inspectDist(seat, lower, higher);

            if (dist > maxDist) {
                index = seat;
                maxDist = dist;
            }
        }

        if (index == -1) {
            throw new RuntimeException();
        }

        return index;
    }

    private static int inspectDist(int from, Integer lower, Integer higher) {

        if (lower == null && higher == null) {
            return N;
        }

        if (lower == null || higher == null) {
            return Math.abs(
                    from - (lower == null ? higher : lower)
            );
        }

        int lDist = Math.abs(lower - from);
        int rDist = Math.abs(from - higher);

        return Math.min(lDist, rDist);
    }

    private static int getTimeDiff(String start, String end) {

        if (start.length() != end.length() || start.length() != 4) {
            throw new IllegalArgumentException();
        }

        int startHour = Integer.parseInt(start.substring(0, 2));
        int startMin = Integer.parseInt(start.substring(2, 4));

        int endHour = Integer.parseInt(end.substring(0, 2));
        int endMin = Integer.parseInt(end.substring(2, 4));

        int startVal = 60 * startHour + startMin;
        int endVal = 60 * endHour + endMin;

        return endVal - startVal;
    }
}

enum Type {
    IN, OUT
}

class Info implements Comparable<Info> {

    final int index, diff;
    final Type type;
    final String time;

    public Info(int index, int diff, Type type, String time) {
        this.index = index;
        this.diff = diff;
        this.type = type;
        this.time = time;
    }

    @Override
    public int compareTo(Info o) {
        int timeComp = time.compareTo(o.time);

        // time first
        if (timeComp != 0) {
            return timeComp;
        }

        // out first
        if (this.type != o.type) {
            return this.type == Type.OUT ? -1 : 1;
        }

        // shortest info first
        return Integer.compare(this.diff, o.diff);
    }
}
