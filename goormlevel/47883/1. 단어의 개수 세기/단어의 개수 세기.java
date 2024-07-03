
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws Exception {
        char[] letters = br.readLine().toCharArray();

        int len = letters.length;
        int index = 0;

        int count = 0;
        while (index < len)     {
            char letter = letters[index];

            if (letter == ' ') {
                while (index < len && letters[index] == ' ')
                    index++;
                continue;
            }

            count++;
            while (index < len && letters[index] != ' ')
                index++;
        }

        System.out.println(count);
    }
}