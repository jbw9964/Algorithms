import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String[] commands = br.readLine().split("");
        int[] numbers = new int[26];

        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        Stack<Double> stack = new Stack<Double>();

        for (String letter : commands) {
            double num1, num2;
            char ascii = letter.charAt(0);

            switch (ascii) {
                case '+' :
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 + num2);
                    break;
            
                case '-' : 
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 - num2);
                    break;

                case '*' : 
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 * num2);
                    break;

                case '/' : 
                    num2 = stack.pop();
                    num1 = stack.pop();
                    stack.push(num1 / num2);
                    break;
                
                default:
                    int value = numbers[ascii - 'A'];
                    stack.push((double) value);
                    break;
            }
        }

        System.out.printf("%.2f\n", stack.pop());
    }
}
