import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );


    public static void main(String[] args) throws IOException, ParseException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        String Start, End, Close;
        Start = tokenizer.nextToken();
        End = tokenizer.nextToken();
        Close = tokenizer.nextToken();

        HashMap<String, Boolean> hashMap = new HashMap<>();

        String inputString;
        while ((inputString = br.readLine()) != null) {
            tokenizer = new StringTokenizer(inputString);

            String time = tokenizer.nextToken();
            String id = tokenizer.nextToken();

            if (time.compareTo(Start) <= 0)         hashMap.put(id, false);
            else if (time.compareTo(End) < 0)       continue;
            else if (time.compareTo(Close) <= 0)    {
                if (hashMap.containsKey(id))        hashMap.put(id, true);
            }
            
            else                                    break;
        }

        int count = 0;
        for (boolean attendance : hashMap.values()) if (attendance) count++;

        System.out.println(count);
    }
}
