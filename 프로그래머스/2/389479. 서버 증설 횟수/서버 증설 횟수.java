import java.util.*;

class Solution {

    public int solution(int[] players, int m, int k) {
        int answer = 0;

        Queue<Integer> serverExpansion = new LinkedList<>();

        for (int time = 0; time < players.length; time++) {

            disposeServers(time, k, serverExpansion);

            int requiredServer = requiredServer(m, players[time]);

            if (serverExpansion.size() >= requiredServer) {
                continue;
            }

            answer += requiredServer - serverExpansion.size();
            while (serverExpansion.size() < requiredServer) {
                serverExpansion.add(time);
            }
        }

        return answer;
    }

    private void disposeServers(int currentTime, int k, Queue<Integer> serverExpansion) {
        Integer expandedTime;
        while ((expandedTime = serverExpansion.peek()) != null &&
                expandedTime + k <= currentTime) {
            serverExpansion.poll();
        }
    }

    private int requiredServer(int m, int currentPlayers) {
        return currentPlayers / m;
    }
}
