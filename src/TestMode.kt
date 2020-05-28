import kotlin.random.Random

class TestMode(var nimPerfect: NimPerfect = NimPerfect(),
               var nim: Nim = Nim()) {
    fun randomRows(): IntArray{
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
        var nimsTurn = Random.nextBoolean()
                println("${if (nimsTurn) "Nim" else "NimPerfect"} starts!")
        val randRows = randomRows() //intArrayOf(2,4,4,6)
            print("Rows: ")
            randRows.forEach { row -> print("$row ") }
            println()
        var nimBoard = Nim(randRows)

        val nimShouldWin = isWinning(nimBoard.rows) && nimsTurn
            if (nimShouldWin) print("Nim") else print("NimPerfect")
            println(" should win!")

        while (true){
            val move = if (nimsTurn) nimBoard.bestMove()
                else NimPerfect(rows = nimBoard.rows).bestMove()

            nimBoard = nimBoard.play(move)
            if (nimBoard.isGameOver()){
                if (nimsTurn) println("Nim has wone")
                if (!nimsTurn) println("NimPerfect has wone")
                break
            }
            nimsTurn = !nimsTurn
        }
        playGames(n + 1)
    }
}