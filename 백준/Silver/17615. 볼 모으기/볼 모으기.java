import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        char[] letters = br.readLine().toCharArray();

        System.out.println(Math.min(
                inspectCnt('R', letters),
                inspectCnt('B', letters)
        ));
    }

    private static int inspectCnt(char leftBall, char[] balls) {

        int index = 0, len = balls.length;
        for (char ball : balls) {
            if (ball != leftBall) {
                break;
            }
            index++;
        }

        int moveToRight = 0, moveToLeft = 0;

        while (index < len) {

            char ball = balls[index];
            int cnt = countSameBalls(index, balls);

            if (ball == leftBall) {
                moveToLeft += cnt;
                index += cnt;
                continue;
            }

            if (index + cnt < len) {
                moveToRight += cnt;
                index += cnt;
                continue;
            }

            break;
        }

        return Math.min(moveToRight, moveToLeft);
    }

    private static int countSameBalls(int index, char[] balls)  {
        int cnt = 0;

        for (int i = index; i < balls.length; i++) {
            if (balls[i] != balls[index]) {
                break;
            }
            cnt++;
        }

        return cnt;
    }
}
