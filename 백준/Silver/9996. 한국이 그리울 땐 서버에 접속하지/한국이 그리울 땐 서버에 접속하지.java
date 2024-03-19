import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        String regx = br.readLine().replace("*", "\\w*");

        Pattern pattern = Pattern.compile(regx);

        for (int count = 0; count < N; count++) {
            String inputString = br.readLine();

            Matcher matcher = pattern.matcher(inputString);

            
            if (matcher.matches())  sb.append("DA\n");
            else                    sb.append("NE\n");
        }

        System.out.println(sb.toString());
    }
}