import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static List<Integer>[] partyPeopleAdj;
    private static List<Integer>[] peoplePartyAdj;
    private static Set<Integer> inits;

    @SuppressWarnings("unchecked")
    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>(cnt);
        for (int i = 0; i < cnt; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        partyPeopleAdj = IntStream.range(0, M)
                .mapToObj(i -> new ArrayList<>(50))
                .toArray(ArrayList[]::new);
        peoplePartyAdj = IntStream.rangeClosed(0, N)
                .mapToObj(i -> new ArrayList<>(M))
                .toArray(ArrayList[]::new);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                int people = Integer.parseInt(st.nextToken());
                partyPeopleAdj[i].add(people);
                peoplePartyAdj[people].add(i);
            }
        }

        inits = new HashSet<>();
        for (int people : list) {
            List<Integer> parties = peoplePartyAdj[people];
            inits.addAll(parties);
        }
    }

    public static void main(String[] args) throws IOException {

        init();
        
        boolean[] visit = new boolean[M];
        for (int party : inits) {
            visit[party] = true;
        }

        Queue<Integer> q = new ArrayDeque<>(inits);
        while (!q.isEmpty()) {

            int party = q.poll();

            for (int joinPeople : partyPeopleAdj[party]) {
                for (int nextParty : peoplePartyAdj[joinPeople]) {
                    if (!visit[nextParty]) {
                        visit[nextParty] = true;
                        q.add(nextParty);
                    }
                }
            }
        }

        int cnt = M;
        for (int i = 0; i < M; i++) {
            if (visit[i]) {
                cnt--;
            }
        }

        System.out.println(cnt);
    }
}