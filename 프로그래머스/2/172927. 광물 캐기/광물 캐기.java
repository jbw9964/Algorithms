

class Solution {
    private static int MIN_ANSWER = Integer.MAX_VALUE;

    public int solution(int[] picks, String[] minerals) {
        compSearch(picks, minerals, 0, 0);
        return MIN_ANSWER;
    }

    private static void compSearch(int[] picks, String[] minerals, int index, int cost)  {

        if (index >= minerals.length ||
            picks[0] == 0 && picks[1] == 0 && picks[2] == 0)    {
                MIN_ANSWER = Math.min(MIN_ANSWER, cost);
                return;
            }

        int[] costs = calcCost(minerals, index);
        
        if (picks[0] != 0)  {
            picks[0]--;
            compSearch(picks, minerals, index + 5, cost + costs[0]);
            picks[0]++;
        }

        if (picks[1] != 0)  {
            picks[1]--;
            compSearch(picks, minerals, index + 5, cost + costs[1]);
            picks[1]++;
        }

        if (picks[2] != 0)  {
            picks[2]--;
            compSearch(picks, minerals, index + 5, cost + costs[2]);
            picks[2]++;
        }
    }

    private static int[] calcCost(String[] minerals, int i)   {
        int[] result = new int[3];
        for (int index = i; index < minerals.length && index < i + 5; index++)  {
            result[0]++;
            result[1]++;
            result[2]++;

            if (minerals[index].equals("diamond"))  {
                result[1] += 4;
                result[2] += 24;
            }
            else if (minerals[index].equals("iron"))    result[2] += 4;
        }

        return result;
    }
}