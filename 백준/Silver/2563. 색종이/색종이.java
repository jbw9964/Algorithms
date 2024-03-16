import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static boolean[][] Canvas = new boolean[100][100];

    public static void main(String[] args) throws IOException {
        int numOfPapers = Integer.parseInt(br.readLine().split(" ")[0]);

        String[] lineString;
        int count = 0;

        int coord_x, coord_y;

        for (int index = 0; index < numOfPapers; index++) {
            lineString = br.readLine().split(" ");
            
            coord_x = Integer.parseInt(lineString[0]) - 1;
            coord_y = Integer.parseInt(lineString[1]) - 1;
            
            for (int i = coord_x; i < coord_x + 10; i++) {
                for (int j = coord_y; j < coord_y + 10; j++) {
                    if (Canvas[i][j])   continue;

                    Canvas[i][j] = true;
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
}