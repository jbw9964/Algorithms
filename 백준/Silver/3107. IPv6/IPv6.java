import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static String input;

    private static void init() throws IOException {
        input = br.readLine();

        int i = input.indexOf("::");

        if (i != -1) {
            int cnt = input.length() - input.replace(":", "").length();

            if (cnt > 7) {
                input = input.replace("::", ":");
            } else {
                StringBuilder sb = new StringBuilder(input);
                for (; cnt < 7; cnt++) {
                    sb.insert(i, ':');
                }

                input = sb.toString();
            }
        }
    }

    public static void main(String[] args) throws IOException {

        init();

        StringBuilder answer = new StringBuilder();
        String[] tokens = input.split(":", 8);

        if (tokens.length != 8) {
            throw new IllegalArgumentException("Invalid input");
        }

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            int size = token.length();

            for (; size < 4; size++) {
                answer.append('0');
            }

            answer.append(token);

            if (i < tokens.length - 1) {
                answer.append(':');
            }
        }

        System.out.println(answer.toString());
    }
}