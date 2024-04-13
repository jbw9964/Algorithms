import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.UnexpectedException;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        while (T-- > 0) {
            int K = Integer.parseInt(br.readLine());

            while (K-- > 0) {
                StringTokenizer tokenizer = new StringTokenizer(br.readLine());
                String operation = tokenizer.nextToken();
                int value = Integer.parseInt(tokenizer.nextToken());

                switch (operation) {
                    case "I" :
                        treeMap.put(value, treeMap.getOrDefault(value, 0) + 1);
                        break;
                
                    case "D" : 
                        if (treeMap.isEmpty())  continue;

                        if (value == 1)         value = treeMap.lastKey();
                        else if (value == -1)   value = treeMap.firstKey();
                        else                    throw new UnexpectedException("Unexpected operation encountered");

                        if (treeMap.get(value) > 1)     {treeMap.put(value, treeMap.get(value) - 1); continue;}
                        else                            treeMap.remove(value);

                        break;

                    default : throw new UnexpectedException("Unexpected operation encountered");
                }
            }

            sb.append(treeMap.isEmpty() ? "EMPTY" : treeMap.lastKey() + " " + treeMap.firstKey()).append("\n");
            treeMap.clear();
        }
        
        System.out.print(sb.toString());
    }
}
