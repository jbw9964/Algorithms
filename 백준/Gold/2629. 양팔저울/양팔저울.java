import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        br.readLine();
        int[] weights = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        boolean[] beads = new boolean[40_000 + 1];
        for (int weight : weights) {

            Set<Integer> targets = new HashSet<>();
            for (int i = 0; i < beads.length; i++) {
                if (beads[i]) {
                    targets.add(i);
                }
            }

            for (int target : targets) {

                int head = Math.abs(target - weight);
                int tail = target + weight;

                beads[head] = true;
                if (tail < beads.length) {
                    beads[tail] = true;
                }
            }

            beads[weight] = true;
        }

        br.readLine();
        Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .mapToObj(i -> beads[i] ? "Y" : "N")
                .forEach(s -> System.out.print(s + " "));
    }
}
