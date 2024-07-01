
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

class Main {
    private static final BufferedReader br = new BufferedReader(
        new InputStreamReader(System.in)
    );

	public static void main(String[] args) throws IOException {
		int N = Integer.parseInt(br.readLine());

        int size = 1;
        long result = 0L;

        while (size <= N)   {
            long edgeSize = N + 1 - size++;
            result += edgeSize * edgeSize;
        }
        
        System.out.println(result);
	}
}
