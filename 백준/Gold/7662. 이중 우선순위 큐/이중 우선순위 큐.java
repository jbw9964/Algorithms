import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        TreeSet<Integer> treeSet = new TreeSet<>();
        HashMap<Integer, Integer> hashMap = new HashMap<>();

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());

            while (K-- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(br.readLine());
                String operation = tokenizer.nextToken();
                int value = Integer.parseInt(tokenizer.nextToken());

                switch (operation) {
                    case "I" :
                        if (hashMap.getOrDefault(value, 0) == 0)    treeSet.add(value);
                        hashMap.put(value, hashMap.getOrDefault(value, 0) + 1);
                        break;
                
                    case "D" : 
                        if (treeSet.isEmpty())  continue;

                        if (value == 1)         value = treeSet.pollLast();
                        else if (value == -1)   value = treeSet.pollFirst();
                        else                    throw new UnexpectedException("Unexpected operation encountered");

                        if (hashMap.get(value) > 1)    treeSet.add(value);
                        hashMap.put(value, hashMap.get(value) - 1);
                        break;

                    default : throw new UnexpectedException("Unexpected operation encountered");
                }
            }

            sb.append(treeSet.isEmpty() ? "EMPTY" : treeSet.last() + " " + treeSet.first()).append("\n");
            hashMap.clear();
            treeSet.clear();
        }
        
        System.out.print(sb.toString());
    }
}
