import kotlin.math.max
import kotlin.random.Random

class Nim(val rows: IntArray = intArrayOf(1,3,5,7),
          val turn: Int = 1,
          val moves: List<Move> = listOf()): NimGame {

    override fun play(vararg moves: Move): Nim {
        var nim = this
        for(m in moves) nim = nim.play(m)
        return nim
    }

    fun play(move: Move): Nim {
        assert(!isGameOver())
        assert(move.row < rows.size && move.number <= rows[move.row])
        var newRows = rows.copyOf()
        newRows[move.row] = max(0,newRows[move.row] - move.number)
        return Nim(rows = newRows,
                turn = turn * -1,
                moves = moves.plus(move))
    }

    override fun bestMove(): Move {
        val random = Random
        assert(!isGameOver())
        var row: Int
        do{
            row = random.nextInt(rows.size)
        }while (rows[row] == 0)
        val number = random.nextInt(rows[row]) + 1
        return Move(row, number)

    }

    override fun isGameOver(): Boolean {
        return rows.all { x -> x == 0 }
    }

    fun possibleMoves(): List<Move>{
        var res = listOf<Move>()
        for (row in rows.indices){
            if (rows[row] == 0) continue
            for (number in rows[row] downTo 1 ){
                if (rows[row] - number >= 0)
                    res = res.plus(Move(row, number))
            }
        }
        return res
    }

    override fun undoMove(): Nim {
        var nim = Nim(rows = rows,
                turn = turn * -1,
                moves = moves.dropLast(1))
        nim.rows[moves.last().row] += moves.last().number
        return nim
    }

    fun lastMove(): Move = moves.last()

    override fun toString(): String {
        var s = ""
        for(i in rows) s += "\n ${"I ".repeat(i)}${if (i == 0) "-" else ""}"
        return "$s\n____________"
    }

    inner class MiniMax(val player: Int = 1) {

        fun max(nim: Nim): Pair<Int, Move> {
            var bestMove: Move = Move(0, 0)
            if (nim.isGameOver()){
                    0 to Move(0, 0)
                }

            var maxValue = Int.MIN_VALUE
            var possibleMoves = nim.possibleMoves()

            for (move in possibleMoves) {
                val value = min(nim.play(move))
                if (value > maxValue){
                    maxValue = value
                    bestMove = move
                }
            }
            return 0 to bestMove
        }

        fun min(nim: Nim): Int {
            if (nim.isGameOver()){
                return 1
            }

            var minValue = Int.MAX_VALUE
            var possibleMoves = nim.possibleMoves()

            for (move in possibleMoves) {
                val value = max(nim.play(move)).first
                if (value < minValue)
                    minValue = value
            }
            return 0
        }
    }
}