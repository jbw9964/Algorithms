import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main (String[] args) throws IOException {

        int[] array;

        while ((array = input()) != null)
        sb.append(solve(array) + "\n");

        System.out.print(sb.toString());
    }

    public static int[] input() throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine(), " ");
        int numOfBlocks = Integer.parseInt(tokenizer.nextToken());

        if (numOfBlocks == 0)       return null;

        int[] heights = new int[numOfBlocks];
        for (int i = 0; i < numOfBlocks; i++)   heights[i] = Integer.parseInt(tokenizer.nextToken());

        return heights;
    }

    public static String solve(int[] heights)  {
        Stack<Integer> stack = new Stack<>();

        long maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            int currentHeight = heights[i];

            if (stack.isEmpty())    {stack.push(i); continue;}

            if (currentHeight <= heights[stack.peek()])  {

                while (!stack.isEmpty() && currentHeight <= heights[stack.peek()])  {
                    int index = stack.pop();
                    int height = heights[index];
                    int width = stack.isEmpty() ? i : i - 1 - stack.peek();
                    long area = ((long) height) * width;

                    maxArea = area > maxArea ? area : maxArea;
                }
            }

            stack.push(i);
        }

        while (!stack.isEmpty())    {
            int index = stack.pop();
            int height = heights[index];
            int width = stack.isEmpty() ? heights.length : heights.length - 1 - stack.peek();
            long area = ((long) height) * width;

            maxArea = area > maxArea ? area : maxArea;
        }

        return String.valueOf(maxArea);
    }
}
