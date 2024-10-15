import java.io.BufferedReader
import java.io.InputStreamReader


fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sb = StringBuilder()

    val array = Array(5) { CharArray(15) }

    for (i in 0..4) {
        val input = readLine().toCharArray()
        for (j in input.indices) {
            array[i][j] = input[j]
        }
    }

    for (i in 0..14) {
        for (j in 0..4) {
            if (array[j][i].code == 0) {
                continue
            }
            sb.append(array[j][i])
        }
    }

    print(sb.toString())
}