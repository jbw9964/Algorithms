import java.util.HashSet;
import java.util.Set;

class Solution {
    private static Set<Set<String>> answerSet = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {

        for (int i = 0; i < banned_id.length; i++)
        banned_id[i] = banned_id[i].replaceAll("\\*", ".");

        compSearch(user_id, banned_id, 0, new HashSet<>());

        return answerSet.size();
    }

    private static void compSearch(
        String[] user_id, String[] regx,
        int index, Set<String> combination
    )  {

        if (index >= regx.length)   {
            answerSet.add(new HashSet<>(combination));
            return;
        }

        for (String id : user_id)   {
            if (!id.matches(regx[index]))   continue;
            if (combination.contains(id))   continue;

            combination.add(id);
            compSearch(user_id, regx, index + 1, combination);
            combination.remove(id);
        }
    }
}