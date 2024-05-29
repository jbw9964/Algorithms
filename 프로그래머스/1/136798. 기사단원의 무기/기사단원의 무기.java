
class Solution {
    public int solution(int number, int limit, int power) {
        int answer = 0;

        for (int num = 1; num <= number; num++)  {
            int numOfFactors = getNumberOfFactors(num);
            answer += limit < numOfFactors ? power : numOfFactors;
        }

        return answer;
    }

    private int getNumberOfFactors(int num) {
        int sqrt = (int) Math.sqrt(num);
        int result = 0;

        for (int i = 1; i <= sqrt; i++)
        if (num % i == 0)   result++;

        result *= 2;
        if (sqrt * sqrt == num) result--;

        return result;
    }
}