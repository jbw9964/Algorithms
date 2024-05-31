
class Solution {
    public String solution(String s) {
        s = s.toLowerCase();
        char[] letters = s.toCharArray();

        for (int i = 0; i < letters.length; i++)    {
            char letter = letters[i];

            if (!Character.isAlphabetic(letter))    continue;

            boolean flag = true;
            int index = i;
            for (; index < letters.length; index++) {
                letter = letters[index];

                if (!Character.isAlphabetic(letter))    break;

                if (flag)   letters[index] = Character.toUpperCase(letter);
                else        letters[index] = Character.toLowerCase(letter);
                flag = !flag;
            }

            i = index;
        }

        String answer = String.valueOf(letters);

        return answer;
    }
}