
class Solution {
    public int solution(String[][] board, int h, int w) {
        String color = board[h][w];

        int[] dr = {0, 0, 1, -1};
        int[] dc = {1, -1, 0, 0};
        
        int answer = 0;

        for (int i = 0; i < 4; i++) {
            int r = h + dr[i];
            int c = w + dc[i];

            if (!(0 <= r && r < board.length && 0 <= c && c < board[0].length)) continue;

            if (color.equals(board[r][c]))  answer++;
        }
        
        return answer;
    }
}