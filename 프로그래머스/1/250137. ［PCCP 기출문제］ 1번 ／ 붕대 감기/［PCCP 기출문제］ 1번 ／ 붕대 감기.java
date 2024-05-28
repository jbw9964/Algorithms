
class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int duration = bandage[0];
        int heal = bandage[1];
        int extraHeal = bandage[2];
        int maxHealth = health;

        int time = 0;
        for (int[] attack : attacks)    {
            int healedTime = attack[0] - time - 1;
            health += heal * healedTime + healedTime / duration * extraHeal;
            health = health > maxHealth ? maxHealth : health;

            health -= attack[1];

            if (health <= 0)    return -1;

            time = attack[0];
        }
        
        return health;
    }
}