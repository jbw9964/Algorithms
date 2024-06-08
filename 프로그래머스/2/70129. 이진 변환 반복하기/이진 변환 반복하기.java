
class Solution {
    public int[] solution(String s) {
        return solve(s, 0, 0);
    }

    private int[] solve(String s, int depth, int num)   {
        if (s.equals("1"))      return new int[]{depth, num};

        String newS = s.replaceAll("0", "");
        num += s.length() - newS.length();

        return solve(
            String.valueOf(Integer.toBinaryString(newS.length())), 
            depth + 1, num
        );
    }
}