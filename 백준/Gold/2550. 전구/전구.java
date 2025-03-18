import java.io.*;
import java.util.*;

class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final Map<Integer, Integer> switchToLight = new HashMap<>();
    private static final Map<Integer, Integer> lightToSwitch = new HashMap<>();

    private static int[] init() throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] switchPose = getArr();
        int[] lightPose = getArr();
        for (int i = 0; i < N; i++) {
            switchToLight.put(lightPose[i], i + 1);
            lightToSwitch.put(i + 1, lightPose[i]);
        }

        return Arrays.stream(switchPose)
                .map(switchToLight::get)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        int[] arr = init();

        int[] dp = new int[arr.length];
        int len = 0;

        Stack<int[]> history = new Stack<>();
        for (int val : arr) {
            int index = Arrays.binarySearch(dp, 0, len, val);
            int insertPoint = index < 0 ? -1 * (index + 1) : index;

            if (insertPoint >= len) {
                len++;
            }

            dp[insertPoint] = val;
            history.add(new int[] {insertPoint, val});
        }

        List<Integer> LIS = new ArrayList<>(len);
        while (!history.isEmpty()) {
            int[] pop = history.pop();
            int index = pop[0];
            int val = pop[1];

            if (index != len - 1)   {
                continue;
            }

            LIS.add(lightToSwitch.get(val));
            len--;
        }

        System.out.println(LIS.size());
        LIS.stream().sorted().forEach(s -> System.out.print(s + " "));
    }

    private static int[] getArr() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
