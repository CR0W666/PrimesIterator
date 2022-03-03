fun main() {
    val primes = Iterable { PrimesIterator(10) }
    primes
    for (prime in primes) println(prime)
}
main()


class PrimesIterator(private val ceil: Int, private var calcMode: Mode = Mode.AMOUNT) : Iterator<Int> {
    enum class Mode { AMOUNT, TO }
    private var counter: Int = 0
    private val foundPrimes: MutableList<Int> = mutableListOf<Int>()

    fun changeMode(newMode: Mode) { calcMode = newMode }

    override fun hasNext(): Boolean = if ( calcMode == Mode.AMOUNT ) foundPrimes.size < ceil else counter == ceil

    override fun next(): Int {
        check( hasNext() )
        println(calcMode)
        //TODO

        foundPrimes.add(0)
        return 1
    }

}