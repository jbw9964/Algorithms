import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();


    public static void main (String[] args) throws IOException {

        String lineInput = br.readLine();
        char[] characters = lineInput.toCharArray();

        boolean flag = true;
        for (int i = 0; i < characters.length && flag;){
            char character = characters[i];

            if (character == '.')   {
                sb.append(".");     i++;
                continue;
            }

            int count = 0;
            for (; i + count < characters.length; count++)  {
                if (characters[i + count] != 'X')   break;

                if (count >= 4) {
                    i += count;
                    count = 0;
                    sb.append("AAAA");
                }
            }

            if (count % 2 != 0)     flag = false;
            else if (count == 2)    sb.append("BB");
            else                    sb.append("AAAA");

            i += count;
        }

        System.out.println(flag ? sb.toString() : "-1");
    }
}
