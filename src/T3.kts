import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.max
import kotlin.random.Random

data class Move(val row: Int, val number: Int)interface NimGame {
    fun play(vararg moves: Move): NimGame
    fun bestMove(): Move
    fun isGameOver(): Boolean
    fun undoMove(): NimGame
    override fun toString(): String
}
class Nim(
        val rows: IntArray = intArrayOf(1,3,5,7),
        private val turn: Int = 1,
        private val moves: List<Move> = listOf(),
        private val Cache: HashMap<Nim, Int> = hashMapOf(),
        private val shiftLeft: Int = ceil(log2(((rows.max()?:0).toDouble()))).toInt()
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

        val nimShouldWin = isWinning(nimBoard.rows) && nimTurn
            if (nimShouldWin) print("Nim") else print("NimPerfect")
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
        playGames(n + 1)
    }

    private fun isWinning(numbers: IntArray): Boolean {
        return numbers.fold(0){ i, j -> i xor j} != 0
    }
}
class UserInterface {
    private val testMode: Boolean = askIfStartTestmode()
    private var nimGame: NimGame = if (testMode) Nim() else chooseOpponent()

    fun start(){
        if (testMode){
            TestMode().playGames()
            if (askToEndGame()) return
            UserInterface().start()
        }else{
            play()
        }
    }

    private fun askIfStartTestmode(): Boolean{
        "Welcome to the Game!\nSelect the Game Mode".printWithBoarders()
        println("1) Start Test mode [t]")
        println("2) Start Playing [p]")
        print("[t|p]: ")
        return readLine().orEmpty().toLowerCase() == "t"
    }

    private fun chooseOpponent(): NimGame {
        "Select Opponent".printWithBoarders()
        println("1) Play against Nim [n]")
        println("2) Play against NimPerfect [np]")
        print("[n|np]: ")
        val nimWasChosen = readLine() == "n"
        println("\nYou have chosen ${if (nimWasChosen) "Nim" else "NimPerfect"}!")
        return if (nimWasChosen) Nim(rows = chooseRows()) else NimPerfect(rows = chooseRows())
    }
    private fun chooseRows(): IntArray{
        "Select Rows".printWithBoarders()
        println("Select preferred numbers in format \"number1-number2-...\" (example: 1-3-5)")
        println("Type in \"r\" to randomly generate the Rows")
        println("Type in \"ENTER\" to use default Rows (3-4-5)")
        print("[Row numbers|r|ENTER]: ")
        var s = readLine()
        val list = arrayListOf<Int>()
        if (s==null) s = ""

        if (s == "r"){
            val r = Random
            val rowSize = r.nextInt(2,5)
            for (i in 0..rowSize) list.add(r.nextInt(2,7))
            list.sort()
            return list.toIntArray()
        }
        s = s.filter { i -> i.isDigit() }
        s.forEach { c -> list.add(Integer.parseInt(c.toString())) }
        return if (list.isEmpty()) intArrayOf(3, 4, 5) else list.toIntArray()
    }
    private fun showBoard(){
        nimGame.toString().printWithBoarders()
    }

    private fun play(){
        showBoard()
        makeMove()
        showBoard()
        if (askHowToContinue()) return
        play()
    }

    private fun makeMove(){
        println("\nMake move manually (0) or let the computer make the move(1)")
        print("[0|1]: ")
        val isManually = readLine() == "0"

        if (isManually){
            manualMove()
        }
        else{
            println("The computer is making the a move...")
            nimGame = nimGame.play(nimGame.bestMove())
        }
    }

    private fun manualMove(){
        println("Type in your Move in the Format: row.number (example: 2.1)")
        print("Move [row.number]: ")
        val s = readLine().orEmpty()

        if (s.length == 3 && s[0].isDigit() && s[2].isDigit()
                && s[1] == '.'){
            val row = s[0].toString().toInt() - 1
            val number = s[2].toString().toInt()
            val move = Move(row, number)
            try {
                nimGame = nimGame.play(move)
            }catch (a: ArrayIndexOutOfBoundsException){
                "ERROR: Your input was incorrect! Try again!".printWithBoarders()
                manualMove()
            }
        }else{
            "ERROR: Your input was incorrect! Try again!".printWithBoarders()
            manualMove()
        }
    }

    private fun askHowToContinue(gameOver: Boolean = nimGame.isGameOver()): Boolean {
        if (gameOver) println("\nThe Game is over!\nReturn to main menu: ENTER")
            else println("\nContinue: ENTER")
        println("End Game: \"q\" or \"e\"")
        println("Undo last move: \"u\"")
        print("[ENTER|q|u]: ")
        val s = readLine().orEmpty().toLowerCase()
        if (gameOver && s ==""){
            UserInterface().start()
            return true
        }
        return when (s){
            "u" -> {
                nimGame = nimGame.undoMove()
                false
            }
            "q" -> true
            "e" -> true
            else -> false
        }
    }

    private fun askToEndGame(): Boolean{
        println("\nTest Completed!")
        println("Go back to main menu: ENTER")
        println("End Game: \"q\" or \"e\"")
        print("[ENTER|q|e]: ")
        return when (readLine().orEmpty().toLowerCase()){
            "q" -> true
            "e" -> true
            else -> false
        }
    }

    private fun String.printWithBoarders(){
        val len = this.length
        println()
        println("-".repeat(len))
        println(this)
        println("-".repeat(len))
    }
}

UserInterface().start()
