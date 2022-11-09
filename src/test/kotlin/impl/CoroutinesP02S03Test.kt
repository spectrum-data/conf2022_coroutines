package impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.longs.shouldBeLessThan
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class CoroutinesP02S03Test : FunSpec() {
    init {
        test("Return correct result") {
            val items = (1..10000).map { Random.nextInt(1,10).toString() }

            val result = withContext(Dispatchers.Default) {
                CoroutinesP02S03.executeAndConcatenate(items.size) { i ->
                    "${items[i]},"
                }
            }.removeSuffix(",")

            result.split(",") shouldContainExactlyInAnyOrder items
        }

        test("Execution time is less than 1000 ms") {
            val time = measureTimeMillis {
                withContext(Dispatchers.Default) {
                    CoroutinesP02S03.executeAndConcatenate(10000) {
                        delay(100)
                        "1"
                    }
                }
            }

            time shouldBeLessThan 1000
        }
    }
}
