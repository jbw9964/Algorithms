import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final int MAX_LEN = toIndex('z') + 1;

    private static int N;
    private static int ANSWER_CNT;
    private static Set<Character>[] neighbors;

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());

        //noinspection unchecked
        neighbors = new Set[MAX_LEN];

        for (int i = 0; i < N; i++) {
            String logic = br.readLine();

            String[] split = logic.split(" => ");
            char from = split[0].charAt(0);
            char to = split[1].charAt(0);

            int fromIndex = toIndex(from);

            if (neighbors[fromIndex] == null) {
                neighbors[fromIndex] = new HashSet<>(MAX_LEN);
            }

            neighbors[fromIndex].add(to);
        }
    }

    private static int toIndex(char alphabet) {

        int index;
        if (alphabet >= 'A' && alphabet <= 'Z') {
            index = alphabet - 'A';
        } else if (alphabet >= 'a' && alphabet <= 'z') {
            index = alphabet - 'a' + ('Z' - 'A' + 1);
        } else {
            throw new IllegalArgumentException("Invalid alphabet");
        }

        return index;
    }


    public static void main(String[] args) throws IOException {

        init();

        StringBuilder answer = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            buildAnswerFrom(c, answer);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            buildAnswerFrom(c, answer);
        }

        System.out.println(ANSWER_CNT);
        System.out.print(answer.toString());
    }

    private static void buildAnswerFrom(char root, StringBuilder dst) {
        List<Character> adjacent = getAdjacent(root);

        if (adjacent.isEmpty()) {
            return;
        }

        for (char adj : adjacent) {
            ANSWER_CNT++;
            dst.append(root).append(" => ").append(adj)
                    .append("\n");
        }
    }

    private static List<Character> getAdjacent(char root) {

        Set<Character> visited = new HashSet<>();
        visited.add(root);

        Queue<Character> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {

            char current = q.poll();

            int index = toIndex(current);
            Set<Character> neighbor = neighbors[index];

            if (neighbor == null || neighbor.isEmpty()) {
                continue;
            }

            for (char next : neighbor) {
                if (visited.add(next)) {
                    q.add(next);
                }
            }
        }

        visited.remove(root);

        return visited.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }
}
