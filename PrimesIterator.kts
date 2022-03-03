fun main() {
    val primes = Iterable { PrimesIterator(10) }
    for (prime in primes) println(prime)
}
main()


class PrimesIterator(private val ceil: Int, var calcMode: mode = mode.AMOUNT) : Iterator<Int> {
    enum class mode { AMOUNT, TO }
    private var counter: Int = 0
    private val foundPrimes: MutableList<Int> = mutableListOf<Int>()

    override fun hasNext(): Boolean = if ( calcMode == mode.AMOUNT ) foundPrimes.size < ceil else counter == ceil

    override fun next(): Int {
        check( hasNext() )

        //TODO

        foundPrimes.add(0)
        return 1
    }

}