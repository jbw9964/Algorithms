class Solution {
    public String solution(String new_id)   {
        new_id = new_id.toLowerCase();

        StringBuilder sb = new StringBuilder();
        for (char character : new_id.toCharArray()) {
            if (Character.isDigit(character))                                   sb.append(character);
            else if (Character.isLetter(character))                             sb.append(character);
            else if (character == '-' || character == '_' || character == '.')  sb.append(character);
        }

        new_id = sb.toString();
        while (new_id.indexOf("..") != -1)  new_id = new_id.replace("..", ".");

        while (new_id.length() > 0 && new_id.charAt(0) == '.')
        new_id = new_id.substring(1);

        while (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.')
        new_id = new_id.substring(0, new_id.length() - 1);
        
        if (new_id.length() == 0)       new_id = "a";
        
        if (new_id.length() >= 16)  {
            new_id = new_id.substring(0, 15);
            if (new_id.charAt(14) == '.')   new_id = new_id.substring(0, 14);
        }

        else if (new_id.length() <= 2)    {
            sb = new StringBuilder(new_id);
            
            while (sb.length() < 3) sb.append(new_id.charAt(new_id.length() - 1));

            new_id = sb.toString();
        }

        String answer = new_id;
        return answer;
    }
}