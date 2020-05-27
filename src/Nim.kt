import kotlin.math.max

class Nim(
        val rows: IntArray = intArrayOf(1,3,5,7),
        val turn: Int = 1,
        val moves: List<Move> = listOf()
): NimGame
{
    var bestMove = Move(0,0)

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
                moves = moves.plus(move)
        )
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
    override fun bestMove() = Move(0,0)
    override fun undoMove(): Nim {
        var nim = Nim(rows = rows,
                turn = turn * -1,
                moves = moves.dropLast(1)
                )
        nim.rows[moves.last().row] += moves.last().number
        return nim
    }
    override fun toString(): String {
        var s = ""
        for(i in rows) s += "\n ${"I ".repeat(i)}${if (i == 0) "-" else ""}"
        return "$s\n____________"
    }

    fun minimax(): Int{
        if (isGameOver()) return turn * -1
        val moves = possibleMoves()
        var bestValue = -turn * 99

        for (move in moves){
            val value = play(move).minimax()

            if (value > bestValue && turn == 1){
                bestValue = value
                bestMove = move
            }
            if (turn == -1 && value < bestValue)
                bestValue = value
        }
        return bestValue
    }

}