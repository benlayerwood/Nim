import kotlin.random.Random

class UserInterface() {
    var nimGame: NimGame = chooseOpponent()

    fun chooseOpponent(): NimGame {
        println("Which Nim-Version do you want to play against?")
        print("Nim (0) or NimPerfect (1): [0|1] ")
        val NimWasChosen = readLine() == "0"
        println("You have chosen ${if (NimWasChosen) "Nim" else "NimPerfect"}")
        return if (NimWasChosen) Nim(rows = chooseRows()) else NimPerfect(rows = chooseRows())
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
            for (i in 0..rowSize) list.add(r.nextInt(1,7))
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
        if (nimGame.isGameOver()){
            println("The Game is over!")
            return
        }
        when (askHowToContinue()){
            "u" -> nimGame = nimGame.undoMove()
            "q" -> return
            "e" -> return
        }
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

    fun askHowToContinue(): String{
        println("Continue: ENTER")
        println("End Game: \"q\" or \"e\"")
        println("Undo last move: \"u\"")
        print("[c|q|u]: ")
        return readLine().orEmpty()
    }
}