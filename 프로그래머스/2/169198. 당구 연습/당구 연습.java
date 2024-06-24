
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        for (int i = 0; i < balls.length; i++)  {
            int ballX = balls[i][0];
            int ballY = balls[i][1];

            int minima = Integer.MAX_VALUE;
            int value = 0;

            if (startY != ballY || startX < ballX)  // left relfection
            minima = Math.min(      
                minima,
                (startX + ballX) * (startX + ballX) 
                + (Math.abs(startY - ballY))
                * (Math.abs(startY - ballY))
            );

            if (startY != ballY || startX > ballX)  // right relfection
            minima = Math.min(      
                minima,
                (Math.abs(startX - (2 * m - ballX))) 
                * (Math.abs(startX - (2 * m - ballX))) 
                + (Math.abs(startY - ballY)) 
                * (Math.abs(startY - ballY))
            );

            if (startX != ballX || startY < ballY)  // up relfection
            minima = Math.min(      
                minima,
                (Math.abs(startX - ballX)) 
                * (Math.abs(startX - ballX)) 
                + (startY + ballY) * (startY + ballY)
            );

            if (startX != ballX || startY > ballY)  // down relfection
            minima = Math.min(      
                minima,
                (Math.abs(startX - ballX)) 
                * (Math.abs(startX - ballX)) 
                + (Math.abs(startY - (2 * n - ballY))) 
                * (Math.abs(startY - (2 * n - ballY)))
            );

            answer[i] = minima;
        }

        return answer;
    }
}