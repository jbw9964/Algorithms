import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static final String[] lines = {
            "\"재귀함수가 뭔가요?\"", "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.",
            "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.", "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\""
    };

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        System.out.println(
                print(new StringBuilder(), 0, N).toString()
        );;
    }

    private static StringBuilder print(StringBuilder sb, int depth, int N) {
        String header = "____".repeat(depth);

        if (depth == N) {
            sb.append(header).append("\"재귀함수가 뭔가요?\"\n")
              .append(header).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        }
        else {
            for (String line : lines)
                sb.append(header).append(line).append("\n");
            print(sb, depth + 1, N);
        }

        sb.append(header).append("라고 답변하였지.\n");

        return sb;
    }
}