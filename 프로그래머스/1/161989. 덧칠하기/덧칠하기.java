
class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int index = 0;

        while (index < section.length)  {
            int current = section[index];

            while (++index < section.length && section[index] < current + m);

            answer++;
        }

        return answer;
    }
}