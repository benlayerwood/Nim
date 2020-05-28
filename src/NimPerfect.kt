import kotlin.math.max
import kotlin.random.Random

class NimPerfect (val rows: IntArray = intArrayOf(1,3,5,7),
                  private val turn: Int = 1,
                  private val moves: Map<Int,Int> = mapOf(),
                  private val random: Random = Random): NimGame {

    override fun isGameOver(): Boolean {
        return rows.all { x -> x == 0 }
    }
    override fun play(vararg moves: Move): NimPerfect {
        var nim = this
        for(m in moves) nim = play(m)
        return nim
    }

    private fun play(move: Move): NimPerfect {
        assert(!isGameOver())
        assert(move.row < rows.size && move.number <= rows[move.row])
        val newRows = rows.copyOf()
        newRows[move.row] = max(0,newRows[move.row] - move.number)
        return NimPerfect(rows = newRows,
                turn = turn * -1,
                moves = moves.plus(move.row to move.number))
    }

    override fun undoMove(): NimPerfect {
        val nim = NimPerfect(rows = rows,
                turn = turn * -1,
                moves = moves.minus(moves.keys.last()))
        nim.rows[moves.keys.last()] += moves.values.last()
        return nim
    }

    private fun randomMove(): Move {
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

    private fun isWinning(numbers: IntArray): Boolean {
        return numbers.fold(0){ i, j -> i xor j} != 0
    }

    override fun toString(): String {
        var s = ""
        for(i in rows) s += "${"I ".repeat(i)}${if (i == 0) "-" else ""}\n"
        return s.dropLast(1)
    }
}