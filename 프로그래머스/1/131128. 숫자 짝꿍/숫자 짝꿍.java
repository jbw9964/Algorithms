import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    private static class Node {
        String letter;
        int repeat;
        Node(String letter, int repeat) {
            this.letter = letter;
            this.repeat = repeat;
        }

        @Override
        public String toString() {
            return letter;
        }
    }
    public String solution(String X, String Y) {
        int[] letterMap1 = new int["abcdefghijklmnopqrstuvwxyz".length()];
        int[] letterMap2 = new int["abcdefghijklmnopqrstuvwxyz".length()];

        for (char digit : X.toCharArray())  letterMap1[digit - '0']++;
        for (char digit : Y.toCharArray())  letterMap2[digit - '0']++;

        List<Node> list = new LinkedList<>();
        for (int i = 0; i < letterMap1.length; i++) {
            int count1 = letterMap1[i];
            int count2 = letterMap2[i];

            if (count1 != 0 && count2 != 0)
            list.add(new Node(String.valueOf((char) (i + '0')), Math.min(count1, count2)));
        }

        Node[] commonDigits = list.toArray(new Node[0]);

        return genGreatestNumber(commonDigits);
    }

    public String genGreatestNumber(Node[] commonDigits)    {
        if (commonDigits == null || commonDigits.length == 0)   return "-1";
        
        Arrays.sort(
            commonDigits,
            (a, b) -> b.letter.compareTo(a.letter)
        );

        if (commonDigits[0].letter.equals("0"))     return "0";

        StringBuilder sb = new StringBuilder();
        for (Node node : commonDigits)  {
            String digit = node.letter;

            sb.append(digit.repeat(node.repeat));
        }

        return sb.toString();
    }
}