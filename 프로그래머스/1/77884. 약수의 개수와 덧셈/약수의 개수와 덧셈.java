
class Solution {
    public int solution(int left, int right) {
        int[] numOfFactors = new int[right + 1];

        for (int num = 1; num <= right; num++)
        for (int mul = num; mul <= right; mul += num)
        numOfFactors[mul]++;

        int answer = 0;
        for (int num = left; num <= right; num++)
        answer += numOfFactors[num] % 2 == 0 ? num : -num;

        return answer;
    }
}