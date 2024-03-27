import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        String[] inputString = br.readLine().split(" ");
        int N = Integer.parseInt(inputString[0]);
        long B = Long.parseLong(inputString[1]);

        int[][] array = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                array[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        
        showArray(multiplyMatrix(array, B));
    }

    public static int[][] multiplyMatrix(int[][] array, long n) {
        if (n == 1)     return array;
        
        int[][] half_array = multiplyMatrix(array, n / 2);
        int[][] result = new int[array.length][array.length];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                int sum = 0;
                for (int k = 0; k < array.length; k++) {
                    sum += (half_array[i][k] * half_array[k][j]) % 1_000;
                }
                result[i][j] = sum % 1_000;
            }
        }

        if (n % 2 == 1) {
            int[][] temp = new int[array.length][array.length];
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++)  temp[i][j] = result[i][j];
            }

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array.length; j++) {
                    int sum = 0;
                    for (int k = 0; k < array.length; k++) {
                        sum += (temp[i][k] * array[k][j]) % 1_000;
                    }
                    result[i][j] = sum % 1_000;
                }
            }
        }

        return result;
    }

    public static void showArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++)   System.out.print(array[i][j] % 1_000 + " ");
            System.out.println();
        }
    }
}
