import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        int remain = input.length() % 3;

        String[] array = new String[remain == 0 ? input.length() / 3 : input.length() / 3 + 1];
        int index = remain != 0 ? remain : 3;
        array[0] = input.substring(0, index);

        for (int i = 1; i < array.length; i++) {
            array[i] = input.substring(index, index + 3);
            index += 3;
        }
        

        for (String str : array) {
            sb.append(
                Integer.toOctalString(
                    Integer.parseInt(str, 2)
                )
            );
        }

        System.out.println(sb.toString());
    }
}