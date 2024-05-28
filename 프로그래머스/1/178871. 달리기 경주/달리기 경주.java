import java.util.HashMap;
import java.util.Map;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> rankIndex = new HashMap<>();
        String[] playerRank = new String[players.length];

        for (int i = 0; i < players.length; i++)    {
            rankIndex.put(players[i], i);
            playerRank[i] = players[i];
        }

        for (String call : callings)    {
            int currentIndex = rankIndex.get(call);
            String passedPlayer = playerRank[currentIndex - 1];

            playerRank[currentIndex] = passedPlayer;
            playerRank[currentIndex - 1] = call;

            rankIndex.put(call, currentIndex - 1);
            rankIndex.put(passedPlayer, currentIndex);
        }
        
        return playerRank;
    }
}