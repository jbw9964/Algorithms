import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        List<Integer> fruits = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            int fruit = Integer.parseInt(st.nextToken());
            fruits.add(fruit);
        }

        fruits.sort(Integer::compareTo);

        for (int fruit : fruits)    {
            if (fruit > L)  {
                break;
            }
            L++;
        }

        System.out.println(L);
    }
}
