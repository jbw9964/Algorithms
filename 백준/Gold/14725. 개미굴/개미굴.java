import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int N;
    private static Node root;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        root = new Node(0, "root");

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int size = Integer.parseInt(st.nextToken());

            Queue<String> q = new ArrayDeque<>(size);
            for (int j = 0; j < size; j++) {
                q.offer(st.nextToken());
            }

            root.addChildren(size, q);
        }

    }

    public static void main(String[] args) throws IOException {

        init();

        StringBuilder answer = new StringBuilder();
        root.buildStructure(answer);
        
        System.out.print(answer.substring(1));
    }
}

class Node {

    final int depth;
    final String name;
    final TreeMap<String, Node> childrenMap;

    private static final String DASH = "--";

    public Node(int depth, String name) {
        this.depth = depth;
        this.name = name;
        childrenMap = new TreeMap<>();
    }

    public void addChildren(int size, Queue<String> names) {

        if (size == 0 || names == null || size != names.size()) {
            throw new IllegalArgumentException();
        }

        String childName = names.poll();

        if (!childrenMap.containsKey(childName)) {
            childrenMap.put(childName, new Node(depth + 1, childName));
        }

        if (size > 1) {
            childrenMap.get(childName).addChildren(size - 1, names);
        }
    }

    public void buildStructure(StringBuilder dst) {

        representSelf(dst);
        dst.append('\n');

        for (String childName : childrenMap.navigableKeySet()) {
            childrenMap.get(childName).buildStructure(dst);
        }
    }

    private void representSelf(StringBuilder dst) {
        if (this.depth > 0) {
            for (int iter = 0; iter < depth - 1; iter++) {
                dst.append(DASH);
            }
            dst.append(this.name);
        }
    }
}
