import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static int[] infos;
    private static Map<Integer, Integer> countMap;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        infos = new int[N];
        countMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int b = Integer.parseInt(br.readLine());
            infos[i] = b;
            countMap.put(b, countMap.getOrDefault(b, 0) + 1);
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        int answer = 0;

        for (int removal : countMap.keySet()) {

            int[] arr = genInfoWithout(removal);
            int maxCnt = getMaxCountWith(arr);

            answer = Math.max(answer, maxCnt);
        }

        System.out.println(answer);
    }

    private static int[] genInfoWithout(int b) {

        int removalSize = countMap.get(b);

        int[] result = new int[N - removalSize];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int infoB = infos[i];

            if (infoB == b) {
                continue;
            }

            result[cnt++] = infoB;
        }

        return result;
    }

    private static int getMaxCountWith(int[] arr) {
        int maxima = 0;

        int index = 0;
        while (index < arr.length) {

            int pivot = arr[index];

            int add = 0;
            while (index + add < arr.length && arr[index + add] == pivot) {
                add++;
            }

            maxima = Math.max(maxima, add);
            index += add;
        }

        return maxima;
    }
}
