import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] heights;
    private static int[] nearestIndex;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        heights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        init();

        nearestIndex = new int[N];
        Arrays.fill(nearestIndex, -1);

        List<Integer> leftSide = doThings(0, i -> i + 1, i -> i < N);
        List<Integer> rightSide = doThings(N - 1, i -> i - 1, i -> i >= 0);

        assert leftSide.size() == rightSide.size() && leftSide.size() == N;

        StringBuilder answer = new StringBuilder();

        for (int i = 0; i < N; i++) {

            int size = leftSide.get(i) + rightSide.get(N - i - 1);

            answer.append(size).append(" ");

            if (size > 0)   {
                answer.append(nearestIndex[i] + 1);
            }

            answer.append("\n");
        }

        System.out.println(answer.toString());
    }

    private static List<Integer> doThings(
            int start,
            Function<Integer, Integer> indexStepFunc,
            Predicate<Integer> continuePredicate
    ) {

        List<Integer> result = new ArrayList<>(N);

        int index = start;
        Stack<Integer> stack = new Stack<>();

        while (continuePredicate.test(index)) {

            int currentHeight = heights[index];

            while (!stack.isEmpty() && heights[stack.peek()] <= currentHeight) {
                stack.pop();
            }

            int size = stack.size();

            result.add(size);

            if (size != 0
                && (
                        nearestIndex[index] == -1 ||
                        Math.abs(index - stack.peek()) < Math.abs(index - nearestIndex[index])
                )
            ) {
                nearestIndex[index] = stack.peek();
            }

            stack.push(index);
            index = indexStepFunc.apply(index);
        }

        return result;
    }
}
