import java.time.*;
import java.time.format.*;
import java.util.*;

class Solution {

    private static long currentDay;
    private static Map<String, Long> termsMap;

    private static void init(String today, String[] terms, String[] privacy) {

        currentDay = toDay(today);
        termsMap = new HashMap<>();

        for (String term : terms) {

            String[] split = term.split(" ");

            String type = split[0];
            long duration = 28 * Long.parseLong(split[1]);

            termsMap.put(type, duration);
        }
    }

    public int[] solution(String today, String[] terms, String[] privacy) {

        init(today, terms, privacy);

        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacy.length; i++) {

            String[] split = privacy[i].split(" ");

            long enrolledDay = toDay(split[0]);
            long termDuration = termsMap.get(split[1]);

            long end = enrolledDay + termDuration;

            if (end <= currentDay) {
                answer.add(i + 1);
            }
        }

        return answer.stream()
                .mapToInt(Integer::intValue)
                .sorted()
                .toArray();
    }

    private static long toDay(String date) {

        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]) - 2000;
        int month = Integer.parseInt(parts[1]) - 1;
        int day = Integer.parseInt(parts[2]) - 1;

        return (12L * 28L) * year + (28L * month) + day;
    }
}
