import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        System.out.println(
                br.readLine().toCharArray()[Integer.parseInt(br.readLine()) - 1]
        );
    }
}
