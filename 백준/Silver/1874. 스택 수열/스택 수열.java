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
        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<Integer>();
        int pushCursor = 1;

        boolean flag = true;
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());

            while (pushCursor <= number) {
                stack.push(pushCursor++);
                sb.append("+\n");
            }

            if (stack.peek() == number) {
                stack.pop();
                sb.append("-\n");
            }
            else    {
                flag = false;
                break;
            }
        }

        System.out.println(flag ? sb.toString() : "NO");
    }

/*
 *  stack 이 pop 한 값을 기록할 pushCursor 를 이용한다. 
 *  pushCursor 는 stack 이 push 했었던 값 중 (가장 큰 수 + 1) 을 지칭한다.
 *  즉, pushCursor 는 (push 해야 할 상황이라면) stack 에 push 해야될 수를 나타낸다.
 * 
 *  때문에 pushCursor 보다 크거나 같은 값을 만나면 stack 에 추가적으로 push 해야되는 상황이고,
 *  작은 값을 만나면 push 없이 pop 해야하는 상황이다.
 *  
 *  문제에서 stack 은 항상 오름차순으로 삽입된다 하였기 때문에 pushCursor 보다 작은 값들은
 *  stack 에 이미 존재하거나, 이미 pop 된 값들 뿐이다.
 *  
 *  이를 통해 이전에 push, pop 했던 값들을 stack 에 push 하지 않을 수 있다.
 */

}
