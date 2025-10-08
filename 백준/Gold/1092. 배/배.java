import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] cranes;
    private static List<Integer> boxes;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        cranes = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();

        M = Integer.parseInt(br.readLine());
        boxes = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws IOException {

        init();

        if (boxes.isEmpty() || cranes[0] < boxes.get(0)) {
            System.out.println(-1);
            return;
        }

        int answer = 0;
        while (!boxes.isEmpty())    {

            int ci = 0;
            int bi = 0;
            while (ci < cranes.length && bi < boxes.size())  {

                int crane = cranes[ci];
                int box = boxes.get(bi);

                if (crane >= box)   {
                    boxes.remove(bi);
                    ci++;
                }
                else {
                    bi++;
                }
            }

            answer++;
        }

        System.out.println(answer);
    }
}
