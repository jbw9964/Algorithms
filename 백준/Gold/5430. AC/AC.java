import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main (String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < T; i++) {
            char[] commands = br.readLine().toCharArray();
            br.readLine();
            String[] array = br.readLine().replace("[", "").replace("]", "").split(",");
            
            for (String value : array)  if (value.length() != 0)    deque.push(Integer.parseInt(value));

            boolean reverseOrder = false;
            boolean errorFlag = false;
            for (char command : commands)   {
                if (command == 'R')     {reverseOrder = !reverseOrder; continue;}

                if (deque.isEmpty())    {errorFlag = true; break;}

                if (reverseOrder)       deque.pollFirst();
                else                    deque.pollLast();
            }

            if (errorFlag)              sb.append("error\n");
            else if (deque.isEmpty())   sb.append("[]\n");
            else    {
                sb.append("[");

                if (reverseOrder)   while (!deque.isEmpty())    sb.append(deque.pollFirst() + ",");
                else                while (!deque.isEmpty())    sb.append(deque.pollLast() + ",");

                sb.deleteCharAt(sb.length() - 1);
                sb.append("]\n");
            }
        }

        System.out.print(sb.toString());
    }
}
