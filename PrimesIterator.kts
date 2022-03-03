fun main() {
    val primes = PrimesIterator(10)
    while (primes.hasNext()) println(primes.next())
}
main()


class PrimesIterator(private val ceil: Int, private var calcMode: Mode = Mode.AMOUNT) : Iterator<Int> {
    public enum class Mode { AMOUNT, TO }
    private var counter: Int = 0
    private val foundPrimes: MutableList<Int> = mutableListOf<Int>()

    fun changeMode(newMode: Mode) { calcMode = newMode }

    override fun hasNext(): Boolean = if ( calcMode == Mode.AMOUNT ) foundPrimes.size < ceil else (counter <= ceil && next() < ceil)



    override fun next(): Int {
        check( hasNext() )

        val foundPrime: Int? = firstToOrNull(ceil)
        if (foundPrime == null) return ceil
        
        foundPrimes.add(foundPrime)
        return foundPrime
    }


    private fun firstToOrNull(ceil: Int): Int? {
        for (num in counter until ceil) {
            counter++
            if (isPrime(num)) return num
        }
        return null
    }
    private fun isPrime(number: Int): Boolean {
        for (it in 2 until number)
            if (number % it == 0) return true

        return false
    }
    private fun preComputeTo(ceil: Int): List<Int> = (2..ceil).filter { num -> (2 until num).none { num % it == 0 } }



}