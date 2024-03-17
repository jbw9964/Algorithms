import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.UnexpectedException;

public class Main {
    
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        
        int month = Integer.parseInt(input[0]) - 1;
        int day = Integer.parseInt(input[1]);

        String[] days = {
            "SUN", "MON", "TUE", 
            "WED", "THU", "FRI", "SAT"
        };

        int diff = day;

        while (month > 0) {
            switch (month) {
                case 2 :
                    diff += 28;
                    break;
            
                case 4 : 
                case 6 : 
                case 9 : 
                case 11 : 
                    diff += 30;
                    break;

                
                case 1 : 
                case 3 : 
                case 5 : 
                case 7 : 
                case 8 : 
                case 10 : 
                case 12 : 
                    diff += 31;
                    break;

                default:
                    throw new UnexpectedException(
                        "Unexpected month encountered : " + month
                    );
            }
            
            month--;
        }

        System.out.println(days[diff % 7]);
    }
}
