import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Stack;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main (String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Stack<Character> stack = new Stack<>();

        int count = 0;
        for (int i = 0; i < N; i++) {

            for (char character : br.readLine().toCharArray())  {

                if (stack.isEmpty() || stack.peek() != character)   stack.push(character);
                else if (stack.peek() == character)                 stack.pop();
            }

            if (stack.isEmpty())    count++;
            stack.clear();
        }

        System.out.print(count);
    }
}
