import kotlin.math.max
import kotlin.random.Random

class NimPerfect (val rows: IntArray = intArrayOf(1,3,5,7),
                  val turn: Int = 1,
                  val moves: Map<Int,Int> = mapOf(),
                  val random: Random = Random): NimGame {

    override fun isGameOver(): Boolean {
        return rows.all { x -> x == 0 }
    }
    override fun play(vararg moves: Move): NimPerfect {
        var nim = this
        for(m in moves) nim = play(m)
        return nim
    }

    fun play(move: Move): NimPerfect {
        assert(!isGameOver())
        assert(move.row < rows.size && move.number <= rows[move.row])
        var newRows = rows.copyOf()
        newRows[move.row] = max(0,newRows[move.row] - move.number)
        return NimPerfect(rows = newRows,
                turn = turn * -1,
                moves = moves.plus(move.row to move.number))
    }

    override fun undoMove(): NimPerfect {
        var nim = NimPerfect(rows = rows,
                turn = turn * -1,
                moves = moves.minus(moves.keys.last()))
        nim.rows[moves.keys.last()] += moves.values.last()
        return nim
    }

    fun randomMove(): Move {
        assert(!isGameOver())
        var row: Int
        do{
            row = random.nextInt(rows.size)
        }while (rows[row] == 0)
        val number = random.nextInt(rows[row]) + 1
        return Move(row, number)
    }

    override fun bestMove(): Move {
        assert(!isGameOver())
        var tmp: NimPerfect
        for (row in rows.indices){
            if (rows[row] == 0) continue
            for (number in rows[row] downTo 1 ){
                tmp = this.play(Move(row, number))
                if (!isWinning(tmp.rows) || tmp.isGameOver())
                    return Move(row, number)
            }
        }
        return randomMove()
    }

    override fun toString(): String {
        var s = ""
        for(i in rows) s += "\n ${"I ".repeat(i)}${if (i == 0) "-" else ""}"
        return "$s\n____________"
    }
}