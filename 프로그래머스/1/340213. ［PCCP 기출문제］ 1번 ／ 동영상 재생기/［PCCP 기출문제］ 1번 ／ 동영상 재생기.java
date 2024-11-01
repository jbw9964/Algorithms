import java.io.*;

public class Solution {

    public String solution(String video_len, String pos,
            String op_start, String op_end, String[] commands) {

        Video vid = new Video(video_len, pos, op_start, op_end);

        for (String cmd : commands) {
            vid.move(cmd);
        }

        return vid.toMin();
    }

    static class Video {
        static final int UNIT = 10;

        int start, end, cursor;
        int opStart, opEnd;

        public Video(String videoLen, String pos, String opStart, String opEnd) {
            start = 0;
            end = toSec(videoLen);
            cursor = toSec(pos);
            this.opStart = toSec(opStart);
            this.opEnd = toSec(opEnd);

            System.out.println(this.opStart);
            System.out.println(this.opEnd);
            System.out.println(cursor);
            if (this.opStart <= cursor && cursor <= this.opEnd) {
                cursor = this.opEnd;
            }
        }

        public void move(String cmd) {
            if ("next".equals(cmd)) {
                cursor = Math.min(cursor + UNIT, end);
                if (this.opStart <= cursor && cursor <= this.opEnd) {
                    cursor = this.opEnd;
                }
                return;
            }

            if ("prev".equals(cmd)) {
                cursor = Math.max(cursor - UNIT, start);
                if (this.opStart <= cursor && cursor <= this.opEnd) {
                    cursor = this.opEnd;
                }
                return;
            }

            throw new RuntimeException("Invalid command: " + cmd);
        }

        private static int toSec(String time) {
            String[] info = time.split(":");
            int min = Integer.parseInt(info[0]);
            int sec = Integer.parseInt(info[1]);

            return 60 * min + sec;
        }

        private static String toMin(int time) {
            int min = time / 60;
            int sec = time % 60;

            return String.format("%02d:%02d", min, sec);
        }

        public String toMin() {
            return toMin(cursor);
        }
    }
}