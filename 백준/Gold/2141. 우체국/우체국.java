import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Village[] villages = new Village[N];
        long peopleMidian = 0;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int pos = Integer.parseInt(st.nextToken());
            int peoples = Integer.parseInt(st.nextToken());

            villages[i] = new Village(pos, peoples);
            peopleMidian += peoples;
        }

        Arrays.sort(villages);
        peopleMidian = (peopleMidian + 1) / 2;

        int ans = 0;

        for (Village v : villages) {
            peopleMidian -= v.peoples;

            if (peopleMidian <= 0)  {
                ans = v.pos;
                break;
            }
        }

        System.out.println(ans);
    }
}

class Village implements Comparable<Village> {
    final int pos;
    final long peoples;

    public Village(int pos, long peoples) {
        this.pos = pos;
        this.peoples = peoples;
    }

    @Override
    public int compareTo(Village o) {
        return pos - o.pos;
    }
}