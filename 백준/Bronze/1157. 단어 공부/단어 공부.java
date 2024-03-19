import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        String inputString = br.readLine().toUpperCase();

        int[] countArray = new int[26];

        int max = -1;
        int max_index = -1;
        for (int i = 0; i < inputString.length(); i++) {
            countArray[inputString.charAt(i) - 'A']++;

            if (countArray[inputString.charAt(i) - 'A'] > max) {
                max = countArray[inputString.charAt(i) - 'A'];
                max_index = inputString.charAt(i) - 'A';
            }
        }

        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (countArray[i] == max)   count++;
        }

        if (count < 2)     {System.out.println((char) (max_index + 'A'));}
        else                {System.out.println("?");}
    }
}