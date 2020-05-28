import kotlin.random.Random

class TestMode(var nimPerfect: NimPerfect = NimPerfect(),
               var nim: Nim = Nim()) {


    fun randomRows(): IntArray{
        var list = arrayListOf<Int>()
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
        var randRows = randomRows()

        val tm = TestMode(
            NimPerfect(rows = randRows,
                    turn = if (nimsTurn) -1 else 1),
            Nim(rows = randRows,
                    turn = if (nimsTurn) 1 else -1))

        print("Rows:")
        tm.nim.rows.forEach { row -> print("$row ") }
        println()

        val nimShouldWin = isWinning(randRows)

        if (nimShouldWin) println("Nim should win!")
            else println("NimPerfect should win!")
        var nimHasWone = nimShouldWin

        while (true){
        val move = if (nimsTurn) tm.nim.bestMove()
            else tm.nimPerfect.bestMove()

            //println("${if (nimsTurn) "Nim" else "NimPerfect"}Move: $move")

            tm.nim =  tm.nim.play(move)
            tm.nimPerfect = tm.nimPerfect.play(move)

            if (tm.nim.isGameOver()){
                if (nimsTurn) println("Nim has won!")
                else println("NimPerfect has won!")
                playGames(n + 1)
                break
            }

            nimsTurn = !nimsTurn
        }

    }

}