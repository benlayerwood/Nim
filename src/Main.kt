fun main() {
    val ui = UserInterface()
    ui.start()
    //val t = TestMode()
    //t.playGames(39)

}

/*
fun mainTest(){
    var nim = Nim(intArrayOf(1,2))
    Nim.sethashArraySize(nim.rows)
    var moves: List<Move> = listOf()

    for (i  in 0..10){
        var move = nim.possibleMoves().random()
        moves = moves.plus(move)

        nim = nim.play(move)
        if (nim.isGameOver()) break
        Nim.saveInHashArray(nim.rows,nim.turn,Random.nextInt(0,1))
    }

    val a = Nim.hashArray
    println()
}

 */