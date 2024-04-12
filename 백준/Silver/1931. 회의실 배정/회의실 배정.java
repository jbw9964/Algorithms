import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static class Info {
        int start, end, duration;
        Info(int start, int end)   {
            this.start = start;
            this.end = end;
            this.duration = end - start;
        }
        @Override
        public String toString() {
            return String.format("[%d - %d]", start, end);
        }
    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main (String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        
        Info[] inputArray = new Info[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int begin = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            inputArray[i] = new Info(begin, end);
        }

        Arrays.sort(inputArray, new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.start == o2.start ? o1.duration - o2.duration : o1.start - o2.start;
            }
        });

        int answer = 1;
        Info prev = inputArray[0];
        for (int i = 1; i < N; i++) {
            Info current = inputArray[i];
            
            if (prev.end <= current.start)  {
                answer++;
                prev = current; 
                continue;
            }

            prev.end = prev.end < current.end ? prev.end : current.end;
        }

        System.out.println(answer);
    }
}
