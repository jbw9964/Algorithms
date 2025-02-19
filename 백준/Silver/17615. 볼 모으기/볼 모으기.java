import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder().append(br.readLine());

        char[] letters = sb.toString().toCharArray();
        char[] reversed = sb.reverse().toString().toCharArray();

        Object[][] parameters = {
                {'R', letters}, {'B', letters},
                {'R', reversed}, {'B', reversed},
        };

        int ans = Integer.MAX_VALUE;
        for (Object[] param : parameters) {
            ans = Math.min(
                    ans, countMovingRight((char) param[0], (char[]) param[1])
            );
        }

        System.out.println(ans);
    }

    private static int countMovingRight(char leftLetter, char[] letters) {

        int index = 0, len = letters.length;
        int cnt = 0;

        while (index < len) {

            if (letters[index] == leftLetter) {
                index++;
                continue;
            }

            int blockCnt = 1;
            while (index + blockCnt < len && letters[index + blockCnt] != leftLetter) {
                blockCnt++;
            }

            if (index + blockCnt < len) {
                index += blockCnt;
                cnt += blockCnt;
                continue;
            }

            break;
        }

        return cnt;
    }
}
