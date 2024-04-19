import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    
    private static BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static int maxAnswer = Integer.MIN_VALUE;
    private static int minAnswer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        String N = br.readLine();
        compSearch(N, 0);

        System.out.println(minAnswer);
        System.out.println(maxAnswer);
    }

    public static void compSearch(String number, int count)    {
        int oddNums = 0;
        for (char digit : number.toCharArray())
        if ((digit - '0') % 2 == 1)
        oddNums++;

        count += oddNums;

        if (number.length() <= 1)       {
            if (maxAnswer < count)  maxAnswer = count;
            if (minAnswer > count)  minAnswer = count;
            return;
        }

        else if (number.length() == 2)  {
            String str1 = number.substring(0, 1);
            String str2 = number.substring(1, 2);
            compSearch(String.valueOf(Integer.parseInt(str1) + Integer.parseInt(str2)), count);
            return;
        }

        for (int head1 = 1; head1 < number.length(); head1++)  {
            for (int head2 = head1 + 1; head2 < number.length(); head2++)  {

                String str1 = number.substring(0, head1);
                String str2 = number.substring(head1, head2);
                String str3 = number.substring(head2, number.length());

                int newNumber = Integer.parseInt(str1) + Integer.parseInt(str2) + Integer.parseInt(str3);
                compSearch(String.valueOf(newNumber), count);
            }
        }
    }
}
