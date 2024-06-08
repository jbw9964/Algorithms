import java.util.Stack;

class Solution  {
    public int solution(String s)   {
        Stack<Character> stack = new Stack<>();
        char[] letters = s.toCharArray();

        for (char letter : letters)
        if (stack.isEmpty() || stack.peek() != letter)  stack.push(letter);
        else                                            stack.pop();

        return stack.isEmpty() ? 1 : 0;
    }
}