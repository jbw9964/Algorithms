import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main (String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        Long[] arrayTips = new Long[N];
        for (int i = 0; i < N; i++) arrayTips[i] = Long.parseLong(br.readLine());
        Arrays.sort(arrayTips, Collections.reverseOrder());

        long result = 0;
        for (int i = 0; i < N; i++) {
            long tip = arrayTips[i] - i;

            if (tip <= 0)   break;
            result += tip;
        }

        System.out.println(result);
    }
}
