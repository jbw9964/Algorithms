
class Solution {
    public int solution(int n) {
        n--;
        
        int answer = 2;
        while (n % answer != 0)
        answer++;

        return answer;
    }
}