import java.io.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, M;
    private static int[] cranes;
    private static TreeSet<Integer> weightTree;
    private static Map<Integer, Integer> cntMap;

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        cranes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        M = Integer.parseInt(br.readLine());
        List<Integer> weights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .boxed()
                .collect(Collectors.toList());

        weightTree = new TreeSet<>();
        weightTree.addAll(weights);
        cntMap = weights.stream().collect(
                Collectors.groupingBy(w -> w, Collectors.summingInt(w -> 1))
        );
    }


    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;

        while (!weightTree.isEmpty())   {

            int cnt = 0;
            for (int crane : cranes) {

                Integer floor = weightTree.floor(crane);

                if (floor == null)  {
                    continue;
                }

                cntMap.put(floor, cntMap.get(floor) - 1);
                if (cntMap.get(floor) == 0) {
                    weightTree.remove(floor);
                }
                cnt++;
            }

            if (cnt == 0)   {
                answer = -1;
                break;
            }

            answer++;
        }

        System.out.println(answer);
    }

}
