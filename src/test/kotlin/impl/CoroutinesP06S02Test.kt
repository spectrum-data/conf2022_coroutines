package impl

import impl.CoroutinesP06S02.safeLaunchAndJoin
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesP06S02Test : FunSpec() {
    init {
        test("Execution with exception") {
            var resultMessage = ""
            var resultSuppressed = listOf<String>()

            coroutineScope {
                launch {
                    safeLaunchAndJoin(
                        onException = { message, suppressed ->
                            resultMessage = "onException: $message"
                            resultSuppressed = suppressed
                        }
                    ) {
                        launch {
                            delay(100)
                            error("First exception")
                        }
                        launch {
                            try {
                                delay(1000)
                            } finally {
                                error("Suppressed exception")
                            }
                        }
                    }
                }
            }

            assertSoftly {
                resultMessage shouldBe "onException: First exception"
                resultSuppressed shouldContainExactly listOf(
                    "Suppressed exception"
                )
            }
        }
    }
}
