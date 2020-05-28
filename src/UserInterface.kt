import kotlin.random.Random

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
        "You have chosen ${if (nimWasChosen) "Nim" else "NimPerfect"}".printWithBoarders()
        return if (nimWasChosen) Nim(rows = chooseRows()) else NimPerfect(rows = chooseRows())
    }
    private fun chooseRows(): IntArray{
        println("Select preferred numbers in format \"number1-number2-...\" (example: 1-3-5-7)")
        println("Type in \"r\" to randomly generate the Rows")
        print("[Row numbers|r]: ")
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
        return if (list.isEmpty()) intArrayOf(1, 3, 5, 7) else list.toIntArray()
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
            println("Type in your Move in the Format: row.number")
            println("For example: 2.1")
            print("Move (row.number): ")
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
                    makeMove()
                }
            }else{
                "ERROR: Your input was incorrect! Try again!".printWithBoarders()
                makeMove()
            }

        }
        else{
            println("The computer is making the a move...")
            nimGame = nimGame.play(nimGame.bestMove())
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