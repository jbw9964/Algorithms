import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Node implements Comparable<Node> {
        Integer age;
        String name;
        private int order;

        public Node(int age, String name, int order)   {
            this.age = age;
            this.name = name;
            this.order = order;
        }

        @Override
        public int compareTo(Node o) {
            int ageComparison = age.compareTo(o.age);
            return ageComparison == 0 ? this.order - o.order : ageComparison;
        }
    }

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main (String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Node> heap = new PriorityQueue<>();
        
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            heap.add(
                new Node(
                    Integer.parseInt(tokenizer.nextToken()), 
                    tokenizer.nextToken(),
                    i
                )
            );
        }

        while (!heap.isEmpty()) {
            Node node = heap.poll();
            sb.append(node.age).append(" ")
                .append(node.name).append("\n");
        }

        System.out.print(sb.toString());
    }
}
