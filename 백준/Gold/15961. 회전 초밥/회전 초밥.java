import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N, D, K, C;
    private static int INIT_INDEX;
    private static int[] dishes;

    private static final Map<Integer, Integer> dishCntMap = new HashMap<>();

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        dishes = new int[N];

        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
            if (dishes[i] == C) {
                INIT_INDEX = i;
            }
        }

        for (int i = 0; i < K; i++) {
            addDish(
                    getDish(INIT_INDEX, i)
            );
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        boolean containCouponDish = dishCntMap.containsKey(C);
        int MAXIMA = dishCntMap.size();

        int pivot = INIT_INDEX;
        while (N-- > 0)   {

            int targetDish = getDish(pivot, K);
            int removalDish = getDish(pivot, 0);

            addDish(targetDish);
            removeDish(removalDish);

            int size = dishCntMap.size();

            if (size > MAXIMA) {
                MAXIMA = size;
                containCouponDish = dishCntMap.containsKey(C);
            }
            else if (size == MAXIMA) {
                containCouponDish &= dishCntMap.containsKey(C);
            }

            pivot++;
        }

        System.out.println(
                containCouponDish ? MAXIMA : MAXIMA + 1
        );
    }

    private static int getDish(int pivot, int di)  {
        int index = pivot + di;
        return index < dishes.length ?
                dishes[index] : dishes[index % dishes.length];
    }

    private static void addDish(int dish)   {
        dishCntMap.put(
                dish, dishCntMap.getOrDefault(dish, 0) + 1
        );
    }

    private static void removeDish(int dish)   {
        Integer cnt = dishCntMap.get(dish);

        if (cnt <= 1)   {
            dishCntMap.remove(dish);
        }
        else {
            dishCntMap.put(dish, cnt - 1);
        }
    }
}