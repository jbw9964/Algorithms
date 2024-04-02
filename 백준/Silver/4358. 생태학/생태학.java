import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        HashMap<String, Integer> hashMap = new HashMap<>();

        int count = 0;
        String lineInput;
        while ((lineInput = br.readLine()) != null) {
            if (hashMap.containsKey(lineInput))
            hashMap.put(lineInput, hashMap.get(lineInput) + 1);

            else hashMap.put(lineInput, 1);

            count++;
        }

        ArrayList<String> keyList = new ArrayList<>(hashMap.keySet());
        Collections.sort(keyList);
        
        for (String key : keyList) {
            int value = hashMap.get(key);
            sb.append(String.format("%s %.4f\n", key, (double) value / count * 100));
        }

        System.out.println(sb.toString());
    }
}
