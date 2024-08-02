import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Main {
    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    static class Node   {
        int num, count;

        Node(int num, int count) {
            this.num   = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Set<Integer> visited = new HashSet<>();
        visited.add(N);

        Node init = new Node(N, 0);

        Queue<Node> queue = new LinkedList<>();
        queue.add(init);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (curr.num == 1) {
                System.out.println(curr.count);
                return;
            }

            if (curr.num % 3 == 0 && visited.add(curr.num / 3))
                queue.add(new Node(curr.num / 3, curr.count + 1));

            if (curr.num % 2 == 0 && visited.add(curr.num / 2))
                queue.add(new Node(curr.num / 2, curr.count + 1));

            if (visited.add(curr.num - 1))
                queue.add(new Node(curr.num - 1, curr.count + 1));
        }
    }
}
