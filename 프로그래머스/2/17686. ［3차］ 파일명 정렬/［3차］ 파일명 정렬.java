import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Solution {
    public String[] solution(String[] files) {
        LinkedList<String> fileList = new LinkedList<>();
        Map<String, String> headMap = new HashMap<>();
        Map<String, Integer> numberMap = new HashMap<>();

        for (String file : files)   {
            fileList.add(file);
            String head;
            String numStr;

            char[] letters = file.toCharArray();
            int index = 0;
            int cursor = 0;

            while (index < letters.length)  {
                char letter = letters[index];

                if (!Character.isDigit(letter)) {
                    index++;
                    continue;
                }

                while (index + cursor < letters.length 
                    && Character.isDigit(letters[index + cursor]))
                cursor++;

                if (cursor <= 5)    
                break;

                index += cursor;
                cursor = 0;
            }

            head = file.substring(0, index);
            numStr = file.substring(index, index + cursor);

            headMap.put(file, head.toLowerCase());
            numberMap.put(file, Integer.parseInt(numStr));
        }

        Collections.sort(
            fileList, (front, rear) -> {
                String headFront = headMap.get(front);
                String headRear = headMap.get(rear);

                int compare = headFront.compareTo(headRear);
                if (compare != 0)   return compare;
                
                int numFront = numberMap.get(front);
                int numRear = numberMap.get(rear);

                return numFront - numRear;
            }
        );
        
        String[] answer = new String[files.length];
        for (int i = 0; i < answer.length; i++) {
            String file = fileList.pollFirst();
            answer[i] = file;
        }

        return answer;
    }
}