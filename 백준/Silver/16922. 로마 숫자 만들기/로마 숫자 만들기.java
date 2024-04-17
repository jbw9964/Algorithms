import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main   {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static final int[] Nums = {1, 5, 10, 50};

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Set<Integer> set = new HashSet<>();
        for (int num : Nums)    set.add(num);

        while (N-- > 1) {
            Set<Integer> newSet = new HashSet<>();

            for (int prevNum : set)
            for (int num : Nums)
            newSet.add(prevNum + num);

            set = newSet;
        }

        System.out.println(set.size());
    }
}