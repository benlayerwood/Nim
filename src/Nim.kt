import kotlin.math.max
import kotlin.math.pow
import kotlin.random.Random

class Nim(val rows: IntArray = intArrayOf(1,3,5,7),
          val turn: Int = 1,
          val moves: List<Move> = listOf()
): NimGame {
    private var bestMove: Move = Move(0,0)
    var hashArray: Array<Int?> =
            arrayOfNulls<Int>(2.0.pow(rows.size).toInt())
    var hashIndex: Int = 0

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
        max()
        return bestMove
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

    fun max(depth: Int = 0): Int {
        assert(turn == 1)
        if (isGameOver()) return -1
        var maxVal = Int.MIN_VALUE
        val possibleMoves = possibleMoves()

        for (move in possibleMoves){
            val nextNim = play(move)
            var value = 0
            var savedResult = getHashArrayResult(nextNim.rows)

            if (savedResult == 0) {
                value = nextNim.min(depth+1)
                saveInHashArray(value)
            }else {
                println("used saved Result")
                 value = savedResult
            }

            if (value >= maxVal) {
                maxVal = value
                bestMove = move
            }
        }
        return maxVal
    }

    fun min(depth: Int): Int{
        assert(turn == -1)
        if (isGameOver()) return 1
        var minVal = Int.MAX_VALUE
        val possibleMoves = possibleMoves()

        for (move in possibleMoves){
            val nextNim = play(move)
            var value = nextNim.max(depth+1)
            if (value <= minVal) minVal = value
        }
        return minVal
    }

    fun IntArray.toHashCode(): Int{
        var hashCode = 0
        for (row in rows){
            hashCode = (hashCode shl 3) or row
        }
        return hashCode
    }
    fun saveInHashArray(res: Int){
        assert(res == 1 || res == -1)
        var sortedRows = rows.sortedArray()
        var hashCode = sortedRows.toHashCode()
        //add result do HashArray
        val lastBit = if (res == 1) 1 else 0
        hashCode = (hashCode shl 1) or lastBit

        hashArray[hashIndex] = hashCode
        hashIndex++
    }
    //If function returns 0, no Result was found
    fun getHashArrayResult(rows: IntArray): Int{
        var sortedRows = rows.sortedArray()
        val hashCode = sortedRows.toHashCode()
        for (hashItem in hashArray){
            if (hashCode == (hashItem?.shr(1))) {
                val lastBit = hashItem and 1
                return if (lastBit == 0) -1 else 1
            }
        }
        return 0
    }

}