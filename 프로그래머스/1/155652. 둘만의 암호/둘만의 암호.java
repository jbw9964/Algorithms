import java.util.HashSet;
import java.util.Set;

class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();

        Set<Character> skipSet = new HashSet<>();
        for (char letter : skip.toCharArray())
        skipSet.add(letter);

        for (char c : s.toCharArray())
        sb.append(getLetter(c, skipSet, index));

        return sb.toString();
    }

    public char getLetter(char c, Set<Character> skip, int index)   {
        while (index-- > 0) {
            if (!('a' <= ++c && c <= 'z'))
            c = c > 'z' ? 'a' : 'z';

            if (skip.contains(c))   index++;
        }

        return c;
    }
}