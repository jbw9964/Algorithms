import java.util.Arrays;
import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        int[] numbers = {9, 1, 5, 3, 6, 2};

        new Solution().solution(numbers);
    }
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < answer.length; i++)     {

            if (stack.isEmpty() || numbers[stack.peek()] >= numbers[i])   {
                stack.push(i);
                continue;
            }

            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i])
            answer[stack.pop()] = numbers[i];

            stack.push(i);
        }

        return answer;
    }
}