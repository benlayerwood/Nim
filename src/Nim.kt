import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.max

class Nim(
        val rows: IntArray = intArrayOf(1,3,5,7),
        private val turn: Int = 1,
        private val moves: List<Move> = listOf(),
        private val Cache: HashMap<Nim, Int> = hashMapOf(),
        private val shiftLeft: Int = ceil(log2((rows.max()?:0).toFloat())).toInt()
): NimGame
{
    init {
        this.minimax()
    }
    private var bestMove = Move(0,0)

    override fun play(vararg moves: Move): Nim {
        var nim = this
        for(m in moves) nim = nim.play(m)
        return nim
    }
    fun play(move: Move): Nim {
        assert(!isGameOver())
        assert(move.row < rows.size && move.number <= rows[move.row])
        val newRows = rows.copyOf()
        newRows[move.row] = max(0,newRows[move.row] - move.number)
        return Nim(rows = newRows,
                turn = turn * -1,
                moves = moves.plus(move),
                Cache = Cache,
                shiftLeft = shiftLeft
        )
    }
    override fun isGameOver(): Boolean {
        return rows.all { x -> x == 0 }
    }
    private fun possibleMoves(): List<Move>{
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

    override fun bestMove(): Move{
        return possibleMoves().firstOrNull { Cache[this.play(it)] == turn } ?:possibleMoves().random()
    }

    override fun undoMove(): Nim {
        val nim = Nim(rows = rows,
                turn = turn * -1,
                moves = moves.dropLast(1)
                )
        nim.rows[moves.last().row] += moves.last().number
        return nim
    }
    override fun toString(): String {
        var s = ""
        for(i in rows) s += "${"I ".repeat(i)}${if (i == 0) "-" else ""}\n"
        return s.dropLast(1)
    }

    private fun minimax(): Int{
        if (Cache[this] != null) return Cache[this]!!

        if (isGameOver()){
            val res = turn * -1
            Cache[this] = res
            return res
        }

        val moves = possibleMoves()
        var bestValue = if (turn == 1) Int.MIN_VALUE else Int.MAX_VALUE

        for (move in moves){
            val value = play(move).minimax()

            if (value > bestValue && turn == 1){
                bestValue = value
                bestMove = move
            }
            if (turn == -1 && value < bestValue)
                bestValue = value
        }

        Cache[this] = bestValue
        return bestValue
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return other.hashCode() == this.hashCode()
    }

    override fun hashCode(): Int {
        if(isGameOver())
            return turn * Int.MAX_VALUE
        return turn * rows.sorted().fold(0) {acc, i -> (acc shl shiftLeft) + i }
    }

}