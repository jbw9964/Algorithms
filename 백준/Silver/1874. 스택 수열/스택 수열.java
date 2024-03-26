import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        boolean[] popTable = new boolean[N + 1];
        int peekNum = 0;

        boolean flag = true;
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());

            if (number > peekNum) {
                int count = 0;
                for (int j = peekNum + 1; j <= number; j++) {
                    if (!popTable[j])   count++;
                }
                
                peekNum = number;
                sb.append("+\n".repeat(count));
            }

            if (peekNum == number) {
                popTable[peekNum] = true;

                int index = 1;
                while (peekNum - index > 0 && popTable[peekNum - index])  index++;
                
                peekNum -= index;
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
 * 1. peek 한 값보다 큰 수를 만나면 그 수까지 push 한다. 이 때 이전에 pop 한 값들을 push 하지 않도록 한다.
 * 2. (push 를 진행한 후) peek 한 값보다 작은 수를 만나면 이루어 질 수 없는 수열이다.
 * 3. (push 를 진행한 후) peek 한 값과 같은 수를 만나면 pop 한다.
 */

}
