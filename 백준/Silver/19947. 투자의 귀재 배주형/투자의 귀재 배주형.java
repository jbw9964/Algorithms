import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        final int H = Integer.parseInt(st.nextToken());
        final int Y = Integer.parseInt(st.nextToken());

        int answer = H;
        Queue<State> q = new LinkedList<>();
        q.add(new State(H, 0));

        while (!q.isEmpty()) {

            State cur = q.poll();
            int money = cur.money;
            int currentYear = cur.currentYear;

            answer = Math.max(answer, money);

            if (currentYear + 1 <= Y) {
                q.add(
                        new State((int) (money * 1.05d), currentYear + 1)
                );
            }

            if (currentYear + 3 <= Y) {
                q.add(
                        new State((int) (money * 1.20d), currentYear + 3)
                );
            }

            if (currentYear + 5 <= Y) {
                q.add(
                        new State((int) (money * 1.35d), currentYear + 5)
                );
            }
        }

        System.out.println(answer);
    }

}


class State {

    int money, currentYear;

    public State(int money, int currentYear) {
        this.money = money;
        this.currentYear = currentYear;
    }

    @Override
    public String toString() {
        return "State{" +
                "money=" + money +
                ", currentYear=" + currentYear +
                '}';
    }
}

