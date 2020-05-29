import kotlin.random.Random

class TestMode {
    private fun randomRows(): IntArray{
        val list = arrayListOf<Int>()
        val r = Random
        val rowSize = r.nextInt(2,5)
        for (i in 0..rowSize) list.add(r.nextInt(2,7))
        list.sort()
        return list.toIntArray()
    }

    fun playGames(n: Int = 1){
        if (n > 40) return
                println("\nGame $n:")
        var nimTurn = Random.nextBoolean()
                println("${if (nimTurn) "Nim" else "NimPerfect"} starts!")
        val randRows = randomRows()
            print("Rows: ")
            randRows.forEach { row -> print("$row ") }
            println()
        var nimBoard = Nim(randRows)
        val nimShouldWin: Boolean

        val isWinningPos = isWinning(nimBoard.rows)

        nimShouldWin = isWinningPos && nimTurn || !isWinningPos && !nimTurn

        if (nimShouldWin) print("Nim")
        if (!nimShouldWin) print("NimPerfect")
        println(" should win!")

        while (true){
            val move = if (nimTurn) nimBoard.bestMove()
                else NimPerfect(rows = nimBoard.rows).bestMove()

            nimBoard = nimBoard.play(move)
            if (nimBoard.isGameOver()){
                if (nimTurn) println("Nim has won!")
                if (!nimTurn) println("NimPerfect has won!")
                break
            }
            nimTurn = nimTurn.not()
        }

        if (nimShouldWin && !nimTurn || !nimShouldWin  && nimTurn){
            println("Error: Anticipated Winner has not won!")
            return
        }

        playGames(n + 1)
    }

    private fun isWinning(numbers: IntArray): Boolean {
        return numbers.fold(0){ i, j -> i xor j} != 0
    }
}