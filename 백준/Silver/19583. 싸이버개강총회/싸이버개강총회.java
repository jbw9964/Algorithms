import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException, ParseException {
        String[] inputArray = br.readLine().split(" ");

        String Start, End, Close;
        Start = inputArray[0];
        End = inputArray[1];
        Close = inputArray[2];

        HashMap<String, Boolean> hashMap = new HashMap<>();

        String inputString;
        while ((inputString = br.readLine()) != null) {
            inputArray = inputString.split(" ");

            String time = inputArray[0];
            String id = inputArray[1];

            if (time.compareTo(Start) <= 0)         {
                if (hashMap.containsKey(id))        continue;
                else                                hashMap.put(id, false);
            }
            else if (time.compareTo(End) < 0)       continue;
            else if (time.compareTo(Close) <= 0)    {
                if (hashMap.getOrDefault(id, true))     continue;
                else                                    hashMap.put(id, true);
            }
        }

        int count = 0;
        for (String key : hashMap.keySet())     if (hashMap.get(key))   count++;

        System.out.println(count);
    }
}
