import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());

        int[] array = new int[K];
        int cursur = 0;

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0)   cursur--;
            else            array[cursur++] = num;
        }
        
        int sum = 0;
        for (int i = 0; i < cursur; i++)   sum += array[i];

        System.out.println(sum);
    }
}