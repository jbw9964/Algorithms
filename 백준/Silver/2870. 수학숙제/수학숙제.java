import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Main {

    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

    private static StringBuilder sb = new StringBuilder();

    private static boolean isDecimal(char c)    {return '0' <= c && c <= '9';}

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<String> heap = new PriorityQueue<>(
            (a, b) -> a.length() == b.length() ? a.compareTo(b) : a.length() - b.length()
        );

        for (int i = 0; i < N; i++) {
            for (String str : getNumbers(br.readLine()))
            heap.add(str);
        }

        while (!heap.isEmpty()) {
            sb.append(heap.poll() + "\n");
        }

        System.out.print(sb.toString());
    }

    public static String[] getNumbers(String line) {

        LinkedList<String> result = new LinkedList<>();

        char[] characters = line.toCharArray();

        int index = 0;
        while (index < line.length()) {
            char character = characters[index];

            if (isDecimal(character)) {
                int count = 0;
                StringBuilder number = new StringBuilder();

                while (
                    index + count < line.length() 
                    && isDecimal(characters[index + count])
                )  {

                    if (!(characters[index + count] == '0' && number.length() == 0))    number.append(characters[index + count]);
                    count++;
                }

                result.add(number.length() == 0 ? "0" : number.toString());
                index += count;
            }

            index++;
        }

        return result.toArray(new String[0]);
    }
}
