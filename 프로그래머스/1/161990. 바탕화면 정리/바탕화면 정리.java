
class Solution {
    public int[] solution(String[] wallpaper) {
        int mostUpper, mostLower, mostLeft, mostRight;
        mostUpper = mostLeft = Integer.MAX_VALUE;
        mostLower = mostRight = Integer.MIN_VALUE;
        
        for (int i = 0; i < wallpaper.length; i++)  {
            char[] letters = wallpaper[i].toCharArray();
            for (int j = 0; j < letters.length; j++)    {
                if (letters[j] != '#')  continue;

                int up = i;
                int down = i + 1;
                int left = j;
                int right = j + 1;

                mostUpper = mostUpper > up ? up : mostUpper;
                mostLeft = mostLeft > left ? left : mostLeft;
                mostLower = mostLower < down ? down : mostLower;
                mostRight = mostRight < right ? right : mostRight;
            }
        }

        int[] answer = {mostUpper, mostLeft, mostLower, mostRight};
        
        return answer;
    }
}