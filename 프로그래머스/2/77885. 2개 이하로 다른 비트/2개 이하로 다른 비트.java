
class Solution {
    public long[] solution(long[] numbers) {
        for (int i = 0; i < numbers.length; i++)        {
            long num = numbers[i];
            
            if ((num & 1L) == 0 || (num & 2L) == 0)     {
                numbers[i] = num + 1;
                continue;
            }

            int index = 2;
            while (((1L << index) & num) != 0)  index++;

            numbers[i] = (num | (1L << index)) - (1L << (index - 1));
        }
        
        return numbers;
    }
}