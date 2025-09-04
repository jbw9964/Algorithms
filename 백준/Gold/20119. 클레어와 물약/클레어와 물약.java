import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M, L;
    private static List<Integer>[] children;
    private static List<List<Integer>>[] parents;
    private static Set<Integer> availablePotions;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        children = new List[N + 1];
        parents = new List[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            List<Integer> parent = new ArrayList<>(k);

            for (int j = 0; j < k; j++) {
                parent.add(Integer.parseInt(st.nextToken()));
            }

            int r = Integer.parseInt(st.nextToken());
            (parents[r] == null ? parents[r] = new ArrayList<>() : parents[r])
                    .add(parent);

            for (int p : parent)    {
                (children[p] == null ? children[p] = new ArrayList<>() : children[p])
                        .add(r);
            }
        }

        L = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        availablePotions = new HashSet<>(L);

        for (int i = 0; i < L; i++) {
            availablePotions.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        solve();

        List<Integer> answer = availablePotions.stream().sorted().collect(Collectors.toList());
        System.out.println(answer.size());
        for (int potion : answer) {
            System.out.print(potion + " ");
        }
    }

    private static void solve() {

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        for (int available : availablePotions) {
            visited[available] = true;
            List<Integer> candidates = children[available];

            if (candidates != null && !candidates.isEmpty())    {
                q.addAll(candidates);
            }
        }

        while (!q.isEmpty()) {

            Integer current = q.poll();

            List<List<Integer>> parentList = parents[current];
            if (parentList == null || parentList.isEmpty()) {
                continue;
            }

            boolean available = false;
            for (List<Integer> parent : parentList) {
                if (availablePotions.containsAll(parent)) {
                    available = true;
                    break;
                }
            }

            if (!available) {
                continue;
            }

            visited[current] = true;
            availablePotions.add(current);
            List<Integer> candidates = children[current];

            if (candidates != null && !candidates.isEmpty())    {
                for (Integer candidate : candidates) {
                    if (visited[candidate]) {
                        continue;
                    }
                    q.offer(candidate);
                }
            }
        }
    }
}
