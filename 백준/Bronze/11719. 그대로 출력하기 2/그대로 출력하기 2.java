import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        String input = null;
        StringBuilder builder = new StringBuilder();

        while ((input = br.readLine()) != null) {
            builder.append(input)
                .append("\n");
        }

        System.out.println(builder.toString());
    }
}
