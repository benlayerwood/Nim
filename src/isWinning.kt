fun isWinning(numbers: IntArray): Boolean {
    return numbers.reduce{ i, j -> i xor j} != 0
}