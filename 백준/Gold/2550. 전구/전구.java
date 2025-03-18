import java.io.*;
import java.util.*;

// https://one10004.tistory.com/248
// https://gom20.tistory.com/91
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

    private static int[] getArr() throws IOException {
        return Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static void main(String[] args) throws IOException {

        int[] arr = init();

        int[] dp = new int[arr.length];
        int len = 0;

        List<int[]> history = new ArrayList<>(arr.length);
        for (int val : arr) {
            int index = Arrays.binarySearch(dp, 0, len, val);
            int insertPoint = index < 0 ? -1 * (index + 1) : index;

            if (insertPoint >= len) {
                len++;
            }

            dp[insertPoint] = val;
            history.add(new int[] {insertPoint, val});
        }

        Collections.reverse(history);
        int[] answer = new int[len];

        while (!history.isEmpty()) {
            int[] hist = history.remove(0);
            int index = hist[0];
            int val = hist[1];

            if (index != len - 1)   {
                continue;
            }

            answer[--len] = lightToSwitch.get(val);
        }

        Arrays.sort(answer);

        System.out.println(answer.length);
        for (int val : answer) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
