
class Solution {
    public int solution(String t, String p) {

        long num = Long.parseLong(p);

        int answer = 0;
        for (int i = 0; i < t.length() - p.length() + 1; i++)    {
            if (Long.parseLong(t.substring(i, i + p.length())) <= num)
            answer++;
        }

        return answer;
    }
}
