import java.util.stream.IntStream;

class Solution {
    public int solution(int[] absolutes, boolean[] signs) {
        IntStream.range(0, absolutes.length).forEach(i -> {
            absolutes[i] = signs[i] ? absolutes[i] : -absolutes[i];
        });

        return IntStream.of(absolutes).sum();
    }
}