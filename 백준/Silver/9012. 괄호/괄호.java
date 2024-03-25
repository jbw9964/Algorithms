import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String lineInput = br.readLine();
            Stack<Character> stack = new Stack<Character>();
            
            boolean flag = true;
            for (char letter : lineInput.toCharArray()) {

                if (letter == '(')          stack.push(letter);
                else if (letter == ')')     {

                    if (stack.size() == 0)  {
                        flag = false;
                        break;
                    }

                    stack.pop();
                }
            }

            if (stack.size() != 0 ||  !flag)    sb.append("NO\n");
            else                                sb.append("YES\n");
        }

        System.out.println(sb.toString());
    }
}
