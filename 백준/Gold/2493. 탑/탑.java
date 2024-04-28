import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static class Building   {
        int height, index;
        Building(int h, int i)  {
            height = h;
            index = i;
        }
    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Building[] buildings = new Building[N + 1];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++)
        buildings[i] = new Building(Integer.parseInt(tokenizer.nextToken()), i);
        
        int[] answer = new int[N + 1];
        Stack<Building> stack = new Stack<>();

        for (int i = N; i >= 1; i--)    {
            Building building = buildings[i];

            if (stack.isEmpty() || building.height < stack.peek().height)   {
                stack.add(building);
                continue;
            }

            while (!stack.isEmpty() && stack.peek().height <= building.height)   {
                Building prev = stack.pop();
                answer[prev.index] = building.index;
            }
            
            stack.add(building);
        }

        for (int i = 1; i <= N; i++)
        System.out.print(answer[i] + " ");
    }
}