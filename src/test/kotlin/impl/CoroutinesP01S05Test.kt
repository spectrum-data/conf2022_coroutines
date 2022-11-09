package impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.string.shouldStartWith
import kotlin.system.measureTimeMillis

class CoroutinesP01S05Test : FunSpec() {
    init {
        context("Execute time is less than ...") {
            mapOf(
                4 to 1100L,
                8 to 1100L,
                16 to 1100L,
                32 to 1100L,
                64 to 1100L,
                128 to 2100L,
            ).forEach { (parallelism, expectedTime) ->
                test("For $parallelism coroutines execution time is less than $expectedTime ms") {
                    val time = measureTimeMillis {
                        CoroutinesP01S05.executeDataBaseRequest(parallelism) {
                            Thread.sleep(1000)
                        }
                    }

                    time shouldBeLessThan expectedTime
                }
            }
        }

        test("Execute in default thread pool") {
            var threadName = ""
            CoroutinesP01S05.executeDataBaseRequest(2) {
                threadName = Thread.currentThread().name
            }

            threadName shouldStartWith "DefaultDispatcher-worker-"
        }
    }
}
