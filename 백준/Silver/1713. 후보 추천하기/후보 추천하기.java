import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());

        int capacity = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[capacity + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>(n);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sequence = 0;

        while (st.hasMoreTokens()) {

            int index = Integer.parseInt(st.nextToken());
            sequence++;

            // pq.forEach(System.out::println);
            // System.out.println();

            // node already exists in pq
            if (nodes[index] != null) {
                Node node = nodes[index];
                pq.remove(node);
                node.popularity++;
                pq.add(node);
                continue;
            }

            // new node comes in, free space exists
            if (pq.size() < n) {
                nodes[index] = new Node(index, sequence);
                pq.add(nodes[index]);
                continue;
            }

            // pq is full
            Node removal = pq.poll();
            nodes[removal.index] = null;

            // replacement
            nodes[index] = new Node(index, sequence);
            pq.add(nodes[index]);
        }

        List<Node> result = new ArrayList<>(pq);
        result.sort((o1, o2) -> o1.index - o2.index);

        for (Node node : result) {
            System.out.printf("%d ", node.index);
        }
        System.out.println();
    }
}


class Node implements Comparable<Node> {

    final int index, sequence;
    int popularity;

    public Node(int index, int sequence) {
        this.index = index;
        this.sequence = sequence;
        popularity = 1;
    }

    @Override
    public int compareTo(Node o) {
        return popularity != o.popularity ?
                popularity - o.popularity :
                sequence - o.sequence;
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", sequence=" + sequence +
                ", popularity=" + popularity +
                '}';
    }
}
