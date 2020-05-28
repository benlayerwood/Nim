fun isWinning(numbers: IntArray): Boolean {
    return numbers.fold(0){ i, j -> i xor j} != 0
}