import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        String[] array = br.readLine().split(" ");

        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            int num1 = Integer.parseInt(array[i]);

                for (int j = i + 1; j < N; j++) {
                    int num2 = Integer.parseInt(array[j]);

                    if (num1 + num2 == target)  count++;
            }
        }

        System.out.println(count);
    }
}
