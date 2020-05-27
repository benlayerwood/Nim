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
        println("Game $n:")
        val nimStarts = Random.nextBoolean()
        println("${if (nimStarts) "Nim" else "NimPerfect"} starts!")
        var randRows = randomRows()

        val tm = TestMode(
            NimPerfect(rows = randRows,
                    turn = if (nimStarts) -1 else 1),
            Nim(rows = randRows,
                    turn = if (nimStarts) 1 else -1))

        print("Rows:")
        tm.nim.rows.forEach { row -> print("$row ") }
        println()
        val nimShouldWin = !isWinning(tm.nim.rows)
        if (nimShouldWin) println("Nim should win!")
            else println("NimPerfect should win!")
        var nimHasWone = nimShouldWin

        while (true){
        val move = if (nimStarts) tm.nim.bestMove()
            else tm.nimPerfect.bestMove()

            tm.nim =  tm.nim.play(move)
            if (tm.nim.isGameOver()){
                nimHasWone = true
                println("Nim has wone!")
                println("Is Valid: ${nimHasWone == nimShouldWin}\n")
                playGames(n + 1)
                break
            }

            tm.nimPerfect = tm.nimPerfect.play(move)
            if (tm.nimPerfect.isGameOver()){
                 nimHasWone = false
                println("Nim has wone!")
                println("Is Valid: ${nimHasWone == nimShouldWin}\n")
                playGames(n + 1)
                break
            }
        }

    }

}