import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static String S, E, Q;

    private static List<Info> infos;

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        S = st.nextToken();
        E = st.nextToken();
        Q = st.nextToken();

        infos = new ArrayList<>();

        String input;

        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);

            String time = st.nextToken();
            String nickname = st.nextToken();

            infos.add(new Info(time, nickname));
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        Set<String> joinedMemberSet = infos.stream()
                .filter(i -> i.beforeOrEqualThan(S))
                .map(Info::getNickname)
                .collect(Collectors.toSet());

        Set<String> remainedMemberSet = infos.stream()
                .filter(i -> i.afterOrEqualThan(E) && i.beforeOrEqualThan(Q))
                .map(Info::getNickname)
                .collect(Collectors.toSet());

        joinedMemberSet.retainAll(remainedMemberSet);

        int answer = joinedMemberSet.size();
        System.out.println(answer);
    }
}

class Info {

    final String time, nickname;

    public Info(String time, String nickname) {
        this.time = time;
        this.nickname = nickname;
    }

    public boolean beforeOrEqualThan(String time) {
        return this.time.compareTo(time) <= 0;
    }

    public boolean afterOrEqualThan(String time) {
        return this.time.compareTo(time) >= 0;
    }

    public String getNickname() {
        return nickname;
    }
}
