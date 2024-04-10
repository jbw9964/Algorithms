import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int x, y;
        public Node(int x, int y)   {this.x = x; this.y = y;}
        @Override
        public String toString() {
            return String.format("%d %d", x, y);
        }
    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main (String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Node[] array = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            array[i] = new Node(
                Integer.parseInt(tokenizer.nextToken()), 
                Integer.parseInt(tokenizer.nextToken())
            );
        }

        Arrays.sort(array, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.x != o2.x ? o1.x - o2.x : o1.y - o2.y;
            }
        });

        for (Node node : array) sb.append(node).append("\n");

        System.out.print(sb.toString());
    }
}
