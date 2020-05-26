fun main() {
    var nim = Nim(intArrayOf(0,2,0,3))
    println(nim.toString())
    println(nim.MiniMax().max(nim))

    val ui = UserInterface()
    ui.play()
}