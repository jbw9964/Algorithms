
class Coord {
    public int r, c;
    public Coord(int r, int c)              {this.r = r; this.c = c;}
    public void setCoord(int dr, int dc)    {this.r += dr; this.c += dc;}
}

class Solution {
    public int[] solution(String[] park, String[] routes) {
        
        int R = park.length;
        int C = park[0].length();

        // false : moveable     ||  true : unmoveable
        boolean[][] table = new boolean[R][C];
        Coord currentCoord = null;
        
        for (int i = 0; i < R; i++) {
            char[] row = park[i].toCharArray();
            for (int j = 0; j < C; j++)
            if (row[j] == 'X')      table[i][j] = true;
            else if (row[j] == 'S') currentCoord = new Coord(i, j);
        }

        String dirIndex = "NSWE";

        for (String route : routes)   {
            String[] command = route.split(" ");
            int dir = dirIndex.indexOf(command[0]);
            int space = Integer.parseInt(command[1]);

            
            if (!isMoveable(table, currentCoord, dir, space))   continue;
            
            switch (dir) {
                case 0 :    currentCoord.setCoord(-space, 0);   break;
                case 1 :    currentCoord.setCoord(space, 0);    break;
                case 2 :    currentCoord.setCoord(0, -space);   break;
                case 3 :    currentCoord.setCoord(0, space);    break;
            }

        }
        
        int[] answer = {currentCoord.r, currentCoord.c};
        return answer;
    }

    public boolean isMoveable(boolean[][] table, Coord current, int dir, int space) {
        int R = table.length;
        int C = table[0].length;
        
        int r = current.r;
        int c = current.c;

        boolean flag = true;

        switch (dir) {
            case 0 :    while (space-- > 0 && flag) {flag = inRange(--r, c, R, C) && !table[r][c];} break;
            case 1 :    while (space-- > 0 && flag) {flag = inRange(++r, c, R, C) && !table[r][c];} break;
            case 2 :    while (space-- > 0 && flag) {flag = inRange(r, --c, R, C) && !table[r][c];} break;
            case 3 :    while (space-- > 0 && flag) {flag = inRange(r, ++c, R, C) && !table[r][c];} break;
        }

        return flag;
    }

    public boolean inRange(int r, int c, int R, int C)  {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}