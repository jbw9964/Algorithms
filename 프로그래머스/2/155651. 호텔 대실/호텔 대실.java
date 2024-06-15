
class Solution {
    public int solution(String[][] book_time)   {
        int[] countTable = new int[toMinute("24:00")];
        int answer = 1;

        for (String[] booking : book_time)  {
            int start = toMinute(booking[0]);
            int end = toMinute(booking[1]);

            while (start < end + 10 && start < countTable.length)   {
                countTable[start]++;
                answer = Math.max(answer, countTable[start++]);
            }
        }

        return answer;
    }

    private static int toMinute(String time)    {
        String[] info = time.split(":");
        int hour = Integer.parseInt(info[0]);
        int minute = Integer.parseInt(info[1]);

        return 60 * hour + minute;
    }   
}