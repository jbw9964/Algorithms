import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, D, K, C;
    private static int[] dishCnt, dishes;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        dishCnt = new int[D + 1];
        dishes = new int[N];
        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        dishCnt[C]++;
        int typeCnt = 1;

        for (int i = 0; i < K; i++) {
            if (dishCnt[dishes[i]]++ == 0) {
                typeCnt++;
            }
        }

        int MAXIMA = typeCnt;
        for (int pivot = 1; pivot < N; pivot++) {

            int dishToRemove = dishes[getIndex(pivot, -1)];
            int dishToAdd = dishes[getIndex(pivot, K - 1)];

            if (--dishCnt[dishToRemove] == 0) {
                typeCnt--;
            }
            if (dishCnt[dishToAdd]++ == 0) {
                typeCnt++;
            }

            MAXIMA = Math.max(MAXIMA, typeCnt);
        }

        System.out.println(MAXIMA);
    }

    private static int getIndex(int pivot, int di)  {
        int index = pivot + di;
        return index < dishes.length ? index : index % dishes.length;
    }
}