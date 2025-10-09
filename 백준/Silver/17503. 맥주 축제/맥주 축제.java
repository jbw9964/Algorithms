import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, K;
    private static Bear[] bears;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bears = new Bear[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            long preference = Long.parseLong(st.nextToken());
            long degree = Long.parseLong(st.nextToken());
            bears[i] = new Bear(preference, degree);
        }

        Arrays.sort(bears, Bear.lowDegreeFirst());
    }

    public static void main(String[] args) throws IOException {

        init();

        PriorityQueue<Bear> pq = new PriorityQueue<>(Bear.lowPreferenceFirst());

        long preferenceSum = 0;
        long maxDegree = 0;

        for (Bear bear : bears) {

            if (preferenceSum >= M && pq.size() >= N) {
                System.out.println(maxDegree);
                return;
            }

            if (pq.size() < N) {
                pq.add(bear);
                preferenceSum += bear.preference;
                maxDegree = Math.max(maxDegree, bear.degree);
                continue;
            }

            assert !pq.isEmpty();

            Bear lowest = pq.poll();
            Bear target = bear.preference > lowest.preference ? bear : lowest;

            pq.add(target);
            preferenceSum += target.preference - lowest.preference;
            maxDegree = Math.max(maxDegree, target.degree);
        }

        if (preferenceSum >= M && pq.size() >= N) {
            System.out.println(maxDegree);
            return;
        }

        System.out.println(-1);
    }
}

class Bear {

    final long preference, degree;

    public Bear(long preference, long degree) {
        this.preference = preference;
        this.degree = degree;
    }

    public long getPreference() {
        return preference;
    }

    public long getDegree() {
        return degree;
    }

    public static Comparator<Bear> lowPreferenceFirst() {
        return Comparator.comparing(Bear::getPreference);
    }

    public static Comparator<Bear> lowDegreeFirst() {
        return Comparator.comparing(Bear::getDegree);
    }

    @Override
    public String toString() {
        return "Bear{" +
               "preference=" + preference +
               ", degree=" + degree +
               '}';
    }
}
