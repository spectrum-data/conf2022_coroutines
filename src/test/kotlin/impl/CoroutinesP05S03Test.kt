package impl

import impl.CoroutinesP05S03.takeFirstMessages
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.longs.shouldBeLessThan
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

class CoroutinesP05S03Test : FunSpec() {
    init {
        test("Return correct result list") {
            val messages = mapOf(
                "Message 1" to 200L,
                "Message 2" to 400L,
                "Message 3" to 600L,
                "Message 4" to 100L,
                "Message 5" to 500L,
                "Message 6" to 300L,
            )
            val count = 3
            val expectedResult = listOf(
                "Message 4",
                "Message 1",
                "Message 6",
            )

            val deferredList = messages.map { (message, time) ->
                async {
                    delay(time)
                    message
                }
            }

            val result = coroutineScope {
                takeFirstMessages(deferredList, count)
            }

            result shouldContainExactly expectedResult
        }

        test("Execution time is less than 350 ms") {
            val messages = mapOf(
                "Message 1" to 200L,
                "Message 2" to 400L,
                "Message 3" to 2000L,
                "Message 4" to 100L,
                "Message 5" to 500L,
                "Message 6" to 300L,
            )
            val count = 3

            val deferredList = messages.map { (message, time) ->
                async {
                    delay(time)
                    message
                }
            }

            val time = measureTimeMillis {
                coroutineScope {
                    takeFirstMessages(deferredList, count)
                }
            }

            time shouldBeLessThan 350
        }
    }
}
