import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int[] sumAB, sumCD;

    private static void init() throws IOException {

        int N = Integer.parseInt(br.readLine());

        int[][] values = new int[N][4];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            values[i][0] = Integer.parseInt(st.nextToken());
            values[i][1] = Integer.parseInt(st.nextToken());
            values[i][2] = Integer.parseInt(st.nextToken());
            values[i][3] = Integer.parseInt(st.nextToken());
        }

        sumAB = new int[N * N];
        sumCD = new int[N * N];

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumAB[cnt] = values[i][0] + values[j][1];
                sumCD[cnt++] = values[i][2] + values[j][3];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        Arrays.sort(sumAB);
        Arrays.sort(sumCD);

        long ans = 0;

        int head = 0;
        int tail = sumCD.length - 1;

        while (head < sumAB.length && tail >= 0) {
            int sum = sumAB[head] + sumCD[tail];

            if (sum != 0)   {
                head += sum < 0 ? 1 : 0;
                tail -= sum > 0 ? 1 : 0;
                continue;
            }

            long cntH = 1, cntT = 1;

            while (head + 1 < sumAB.length && sumAB[head + 1] == sumAB[head])   {
                head++;
                cntH++;
            }

            while (tail - 1 >= 0 && sumCD[tail - 1] == sumCD[tail])   {
                tail--;
                cntT++;
            }

            ans += cntH * cntT;

            head++;
            tail--;
        }

        System.out.println(ans);
    }
}
