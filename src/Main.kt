fun main() {
    //val ui = UserInterface()
    //ui.play()

    var nim = Nim(intArrayOf(1,3))

    println(nim.possibleMoves())

    println(nim.minimax())
    println(nim.bestMove)
}