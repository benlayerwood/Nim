import kotlin.random.Random

class UserInterface() {
    val testMode: Boolean = askIfStartTestmode()
    var nimGame: NimGame = if (testMode) Nim() else chooseOpponent()

    fun start(){
        if (testMode){
            TestMode().playGames()
            return
        }else{
            play()
        }
    }

    private fun askIfStartTestmode(): Boolean{
        "Welcome to the Game!\nSelect the Game Mode".printWithBoarders()
        println("1) Start Testmode [t]")
        println("2) Start Playing [p]")
        print("[t|p]: ")
        return readLine().orEmpty().toLowerCase() == "t"
    }

    fun chooseOpponent(): NimGame {
        "Select Opponent".printWithBoarders()
        println("1) Play against Nim [n]")
        println("2) Play against NimPerfect [np]")
        println("[n|np]: ")
        val nimWasChosen = readLine() == "0"
        println("You have chosen ${if (nimWasChosen) "Nim" else "NimPerfect"}")
        return if (nimWasChosen) Nim(rows = chooseRows()) else NimPerfect(rows = chooseRows())
    }
    fun chooseRows(): IntArray{
        println("\nPlease choose your preferred Row numbers")
        println("in the format number1-number2-number3...")
        println("for example: 1-3-5-7")
        println("Type in \"r\" to randomly generate the Rows")
        print("Row numbers: ")
        var s = readLine()
        var list = arrayListOf<Int>()
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
        return if (list.isEmpty()) intArrayOf(1, 3, 5, 7) else list.toIntArray()
    }
    fun showBoard(){
        println(nimGame.toString() + "\n")
    }

    fun play(){
        showBoard()
        makeMove()
        showBoard()
        if (askHowToContinue()) return
        play()
    }

    fun makeMove(){
        println("Make move manually (0) or let the computer make the move(1)")
        print("[0|1]: ")
        val isManually = readLine() == "0"
        if (isManually){
            println("Type in your Move in the Format: row.number")
            println("For example: 2.1")
            print("Move (row.number): ")
            var s = readLine()
            if (s != null && s.length == 3){
                val row = s[0].toString().toInt() - 1
                val number = s[2].toString().toInt()
                val move = Move(row, number)
                nimGame = nimGame.play(move)
            }else{
                println("ERROR: Your input was incorrect! Try again!")
                makeMove()
            }

        }
        else{
            println("The computer is making the a move...")
            nimGame = nimGame.play(nimGame.bestMove())
        }
    }

    fun askHowToContinue(gameOver: Boolean = nimGame.isGameOver()): Boolean {
        if (gameOver) println("The Game is over!\nStart new Game: ENTER")
            else println("Continue: ENTER")
        println("End Game: \"q\" or \"e\"")
        println("Undo last move: \"u\"")
        print("[ENTER|q|u]: ")
        val s = readLine().orEmpty().toLowerCase()
        if (gameOver && s ==""){
            UserInterface()
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

    fun String.printWithBoarders(){
        val len = this.length
        println("-".repeat(len))
        println(this)
        println("-".repeat(len))
    }
}