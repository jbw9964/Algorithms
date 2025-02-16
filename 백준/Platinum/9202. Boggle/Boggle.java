import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
    );

    private static int W;
    private static final List<Word> dictWords = new ArrayList<>();

    private static int B;
    private static char[][][] boggleTables;

    private static final StringBuilder sb = new StringBuilder();

    private static void initDictWords() throws IOException {
        W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            String word = br.readLine();
            dictWords.add(new Word(word));
        }
        br.readLine();

        Collections.sort(dictWords);
    }

    private static void initBoggleTables() throws IOException {
        B = Integer.parseInt(br.readLine());

        boggleTables = new char[B][4][];

        for (int i = 0; i < 4; i++) {
            char[] line = br.readLine().toCharArray();
            boggleTables[0][i] = line;
        }

        for (int i = 1; i < B; i++) {
            br.readLine();
            for (int j = 0; j < 4; j++) {
                char[] line = br.readLine().toCharArray();
                boggleTables[i][j] = line;
            }
        }
    }

    public static void main(String[] args) throws IOException {

        initDictWords();
        initBoggleTables();

        for (char[][] boggle : boggleTables) {
            solveBoggle(boggle);
        }

        System.out.print(sb.toString());
    }

    private static void solveBoggle(char[][] boggle) {

        int score = 0, numOfWords = 0;
        String maxWord = null;

        LOOP:
        for (Word word : dictWords) {
            char firstLetter = word.getFirst();

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {

                    char boggleLetter = boggle[i][j];

                    if (boggleLetter != firstLetter) {
                        continue;
                    }

                    if (generableWithDfs(boggle, word, new boolean[4][4], i, j, 0)) {
                        maxWord = maxWord == null ? word.word : maxWord;
                        numOfWords++;
                        score += getScore(word);
                        continue LOOP;
                    }
                }
            }
        }

        sb.append(score).append(" ")
                .append(maxWord).append(" ")
                .append(numOfWords).append("\n");
    }

    private static int getScore(Word word) {
        int len = word.getLength();
        return len <= 2 ? 0 :
                len <= 4 ? 1 :
                len == 5 ? 2 :
                len == 6 ? 3 :
                len == 7 ? 5 : 11;
    }

    private static final int[] di = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dj = {0, 1, 1, 1, 0, -1, -1, -1};

    private static boolean generableWithDfs(
            char[][] boggle, Word word, boolean[][] visit,
            int i, int j, int cnt
    ) {

        if (cnt == word.getLength() - 1) {
            return true;
        }

        visit[i][j] = true;
        char next = word.getIth(cnt + 1);

        for (int k = 0; k < di.length; k++) {

            int newI = i + di[k];
            int newJ = j + dj[k];

            if (!inRange(newI, newJ) || boggle[newI][newJ] != next || visit[newI][newJ]) {
                continue;
            }

            if (generableWithDfs(boggle, word, visit, newI, newJ, cnt + 1)) {
                return true;
            }
        }

        visit[i][j] = false;

        return false;
    }

    private static boolean inRange(int i, int j) {
        return 0 <= i && i < 4 && 0 <= j && j < 4;
    }

    private static void showBoggles() {
        for (char[][] table : boggleTables) {
            for (char[] line : table) {
                System.out.println(String.valueOf(line));
            }
            System.out.println();
        }
    }
}


class Word implements Comparable<Word> {

    char[] letters;
    String word;

    public Word(String word) {
        this.word = word;
        letters = word.toCharArray();
    }

    public char getFirst() {
        return getIth(0);
    }

    public char getIth(int i) {
        return letters[i];
    }

    public int getLength() {
        return letters.length;
    }

    @Override
    public int compareTo(Word o) {
        return letters.length != o.letters.length ?
                o.letters.length - letters.length :
                word.compareTo(o.word);
    }

    @Override
    public String toString() {
        return word;
    }
}