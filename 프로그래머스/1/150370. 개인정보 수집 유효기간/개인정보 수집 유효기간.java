import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termsInfoMap = genTermsInfoMap(terms);

        Deque<Integer> queue = new LinkedList<>();

        for (int i = 0; i < privacies.length; i++)  {
            String dueDate = getDuedate(privacies[i], termsInfoMap);

            if (today.compareTo(dueDate) >= 0)  queue.add(i + 1);
        }

        int[] answer = new int[queue.size()];
        for (int i = 0; i < answer.length; i++)
        answer[i] = queue.pollFirst();
        
        return answer;
    }

    private Map<String, Integer> genTermsInfoMap(String[] terms) {
        Map<String, Integer> termsInfoMap = new HashMap<>();
        for (int i = 0; i < terms.length; i++)  {
            StringTokenizer tokenizer = new StringTokenizer(terms[i]);
            termsInfoMap.put(
                tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken())
            );
        }

        return termsInfoMap;
    }
    private String getDuedate(String privacy, Map<String, Integer> termsInfoMap)   {
        StringTokenizer tokenizer = new StringTokenizer(privacy);

        String[] dateCollected = tokenizer.nextToken().split("\\.");
        int termDuration = termsInfoMap.getOrDefault(tokenizer.nextToken(), -1);

        if (termDuration == -1)     return null;

        int yearCollected = Integer.parseInt(dateCollected[0]);
        int monthCollected = Integer.parseInt(dateCollected[1]);
        int dayCollected = Integer.parseInt(dateCollected[2]);

        monthCollected += termDuration;

        while (monthCollected > 12) {
            monthCollected -= 12;
            yearCollected++;
        }

        String dueDate = String.format(
            "%d.%02d.%02d",
            yearCollected, monthCollected, dayCollected
        );

        return dueDate;
    }
}
