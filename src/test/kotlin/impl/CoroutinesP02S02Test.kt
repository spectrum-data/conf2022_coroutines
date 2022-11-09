package impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import service.ServiceP02S01
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class CoroutinesP02S02Test : FunSpec() {
    init {
        test("Return correct sum") {
            val items = (1..100000).map { Random.nextInt(1,10) }

            val result = withContext(Dispatchers.Default) {
                CoroutinesP02S02.executeAndSum(items.size) { i ->
                    items[i]
                }
            }

            result shouldBe items.sum()
        }

        test("Execution time is less than 2000 ms") {
            val time = measureTimeMillis {
                withContext(Dispatchers.Default) {
                    CoroutinesP02S02.executeAndSum(100000) {
                        delay(100)
                        1
                    }
                }
            }

            time shouldBeLessThan 2000
        }
    }
}
