import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import kotlin.jvm.Throws

@Throws(IOException::class)
fun main(args: Array<String>) = with(BufferedReader(InputStreamReader(System.`in`))) {
    val canvas = Array(100) { BooleanArray(100) }

    val numOfPapers = readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }
        .toTypedArray()[0].toInt()

    var lineString: Array<String>
    var count = 0

    var coord_x: Int
    var coord_y: Int

    for (index in 0 until numOfPapers) {
        lineString =
            readLine().split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        coord_x = lineString[0].toInt() - 1
        coord_y = lineString[1].toInt() - 1

        for (i in coord_x until coord_x + 10) {
            for (j in coord_y until coord_y + 10) {
                if (canvas[i][j]) continue

                canvas[i][j] = true
                count++
            }
        }
    }

    println(count)
}