import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class Node  {
    String str;
    int count;
    Node(String str, int count) {
        this.str = str;
        this.count = count;
    }

    @Override
    public String toString() {
        return String.format("[%s]\t: %d", str, count);
    }
}

class Solution {
    public int solution(String begin, String target, String[] words) {
        Set<String> visitSet = new HashSet<>();
        visitSet.add(begin);

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));

        while (!queue.isEmpty())    {
            Node current = queue.poll();

            for (String word : words)   {
                if (!isConvertable(current.str, word))  continue;
                if (visitSet.contains(word))            continue;

                if (word.equals(target))                return current.count + 1;

                visitSet.add(word);
                queue.add(new Node(word, current.count + 1));
            }
        }
        
        return 0;
    }

    private static boolean isConvertable(String begin, String target) {
        if (begin.equals(target))       return false;
        
        int count = 0;
        char[] arr1 = begin.toCharArray();
        char[] arr2 = target.toCharArray();

        for (int i = 0; i < arr1.length && count < 2; i++)
        if (arr1[i] != arr2[i]) count++;

        return count != 1 ? false : true;
    }
}