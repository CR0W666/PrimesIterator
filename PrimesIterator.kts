
Main().main()
class Main(): PrimesUtils {

    public interface Settings {
        public enum class Mode( var ceil: Int ) { AMOUNT( 10 ), TO( 20 ) }
        public enum class LookAhead( var value: Int ) { NONE( 1 ), NORMAL( 100 ), LARGE( 1000 ) }
    }

    fun main() {
        val primes = PrimesUtils.PrimesIterator(Settings.Mode.TO)
        val found: MutableList<Int> = mutableListOf<Int>()
        while ( primes.hasNext() ) found.add( primes.next() )

        found.forEach { println( it ) }
    }
}


public interface PrimesUtils {
    public fun isPrime( number: Int ): Boolean {

        for ( it in 2 until number )
            if ( number % it == 0 ) return false

        return true
    }

    public fun firstFromToOrNull( floor: Int, ceil: Int ): Int? {

        for ( num: Int in floor until ceil ) {
            println("$num is prime: ${isPrime(num)}")
            if ( isPrime(num) ) return num
        }

        return null

    }

    class PrimesIterator(private var mode: Main.Settings.Mode = Main.Settings.Mode.AMOUNT, private var depth: Int = Main.Settings.LookAhead.LARGE.value) : Iterator<Int>, Main.Settings, PrimesUtils {
        init { if ( mode.ceil < 0 ) mode.ceil *= -1 }



        public var counter: Int = 2
        public val foundPrimes: MutableList<Int> = mutableListOf<Int>()

        public fun changeLookAhead( newValue: Int ) { this.depth = newValue }
        public fun changeMode( newMode: Main.Settings.Mode ) { mode = newMode }


        public val allFound: List<Int>
            get() = foundPrimes.toList()


        override fun hasNext(): Boolean = if ( mode.ceil > 1 ) { if ( mode == Main.Settings.Mode.AMOUNT ) foundPrimes.size < mode.ceil else counter <= mode.ceil } else false

        override fun next(): Int {
            check( hasNext() )
            var result: Boolean = if ( mode.ceil > 1 ) { if ( mode == Main.Settings.Mode.AMOUNT ) foundPrimes.size < mode.ceil else counter <= mode.ceil } else false
            println("$result | mode.ceil > 1 = ${mode.ceil} | mode == amount = ${mode == Main.Settings.Mode.AMOUNT} | size < ceil ${foundPrimes.size < mode.ceil} | cntr <= ceil ${counter <= mode.ceil}")

            val found = nextPrime()
            println(found)
            if( found != null ) foundPrimes.add( found )
            return foundPrimes.last()
        }

        private fun nextPrime( max: Int = depth ): Int? {
            println("nextprime $max | cntr $counter | ${mode.name} ${mode.ceil}")
            if ( mode == Main.Settings.Mode.TO && counter > mode.ceil ) {
                return firstFromToOrNull( counter, mode.ceil )!!
            }

            val found: Int? = firstFromToOrNull( counter, max )

            // EDIT counter += found. Skips numbers.
            return if ( found != null ) { counter += found-1; found } else nextPrime(max * 2)
        }

    }

    //UNUSED - left in cuz its cool
    fun allTo( ceil: Int ): List<Int> = allFromTo(2, ceil)
    fun allFromTo( floor: Int, ceil: Int ): List<Int> = (floor..ceil).filter { num -> (2 until num).none { num % it == 0 } }
}
