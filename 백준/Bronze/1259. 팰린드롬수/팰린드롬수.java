import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        String input;

        LOOP:
        while (!"0".equals(input = br.readLine())) {

            int index = 0;
            char[] digits = input.toCharArray();

            while (index < digits.length / 2)   {

                char head = digits[index];
                char tail = digits[digits.length - index - 1];

                if (head != tail)   {
                    sb.append("no\n");
                    continue LOOP;
                }

                index++;
            }

            sb.append("yes\n");
        }

        System.out.print(sb.toString());
    }
}
