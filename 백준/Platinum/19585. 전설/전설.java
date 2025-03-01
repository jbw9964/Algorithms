import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static final Trie trie = new Trie();
    private static final Set<String> nicknames = new HashSet<>();
    private static String[] teams;

    private static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < C; i++) {
            trie.insert(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            nicknames.add(br.readLine());
        }

        teams = new String[Integer.parseInt(br.readLine())];
        for (int i = 0; i < teams.length; i++) {
            teams[i] = br.readLine();
        }
    }

    public static void main(String[] args) throws IOException {
        init();

        StringBuilder sb = new StringBuilder();

        LOOP:
        for (String team : teams) {
            List<Integer> colorLengths = trie.travelEnds(team);

            for (int len : colorLengths) {
                String tail = team.substring(len);

                if (nicknames.contains(tail)) {
                    sb.append("Yes\n");
                    continue LOOP;
                }
            }

            sb.append("No\n");
        }

        System.out.print(sb.toString());
    }
}

class Trie {

    private static final Node root = new Node('.');

    public Trie() {
    }

    private static char[] toCharArr(String word) {
        char[] chars = new char[word.length() + 1];

        chars[0] = root.letter;
        System.arraycopy(word.toCharArray(), 0, chars, 1, word.length());

        return chars;
    }

    public void insert(String word) {
        root.insert(toCharArr(word), 0);
    }

    public List<Integer> travelEnds(String word) {
        return root.travel(
                toCharArr(word), 0, -1, new ArrayList<>()
        );
    }
}

class Node {

    final char letter;
    boolean end;
    private final Node[] children = new Node[26];

    private static int toIndex(char c) {
        return c - 'a';
    }

    public Node(char letter) {
        this.letter = letter;
    }

    public void insert(char[] word, int index) {
        char ch = word[index];
        assert ch == letter;

        if (index == word.length - 1) {
            end = true;
            return;
        }

        char next = word[index + 1];
        if (children[toIndex(next)] == null) {
            children[toIndex(next)] = new Node(next);
        }
        children[toIndex(next)].insert(word, index + 1);
    }

    public List<Integer> travel(char[] word, int index, int depth, List<Integer> dst) {
        depth++;
        if (end) {
            dst.add(depth);
        }

        if (index == word.length - 1) {
            return dst;
        }

        char next = word[index + 1];
        if (children[toIndex(next)] == null) {
            return dst;
        }

        return children[toIndex(next)].travel(word, index + 1, depth, dst);
    }
}