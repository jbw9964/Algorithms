import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public String solution(int[] numbers) {
        String[] array = new String[numbers.length];
        for (int i = 0; i < array.length; i++)
        array[i] = String.valueOf(numbers[i]);

        Arrays.sort(array, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String case1 = o1 + o2;
                String case2 = o2 + o1;

                return case2.compareTo(case1);
            }
        });

        String answer = String.join("", array);
        
        return answer.charAt(0) == '0' ? "0" : answer;
    }
}