import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static char[] from, to;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        from = br.readLine().toCharArray();
        to = br.readLine().toCharArray();
    }

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();

        while (T-- > 0) {
            init();

            int cntF, cntT, diffCnt;
            cntF = cntT = diffCnt = 0;

            for (int i = 0; i < N; i++) {

                char fromCh = from[i];
                char toCh = to[i];

                if (fromCh != toCh) {
                    diffCnt++;
                }

                if (fromCh == 'W')  {
                    cntF++;
                }

                if (toCh == 'W')  {
                    cntT++;
                }
            }

            int ans = Math.abs(cntF - cntT);
            ans += (diffCnt - Math.abs(cntF - cntT)) / 2;

            answer.append(ans).append("\n");
        }

        System.out.print(answer.toString());
    }
}
