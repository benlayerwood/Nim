interface NimGame {
    fun play(vararg moves: Move): NimGame
    fun bestMove(): Move
    fun isGameOver(): Boolean
    fun undoMove(): NimGame
    override fun toString(): String
}