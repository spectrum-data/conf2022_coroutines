package impl

import impl.CoroutinesP06S01.safeLaunch
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesP06S01Test : FunSpec() {
    init {
        test("Correct execution") {
            var result = ""

            coroutineScope {
                safeLaunch(
                    onCancel = { message ->
                        result = "onCancel: $message"
                    },
                    onException = { message ->
                        result = "onException: $message"
                    }
                ) {
                    delay(100)
                    result = "Job was finished"
                }
            }

            result shouldBe "Job was finished"
        }

        test("Execution with cancellation") {
            var result = ""

            coroutineScope {
                val job = safeLaunch(
                    onCancel = { message ->
                        result = "onCancel: $message"
                    },
                    onException = { message ->
                        result = "onException: $message"
                    }
                ) {
                    delay(1000)
                    result = "Job was finished"
                }

                launch {
                    delay(100)
                    job.cancel("Job was cancelled")
                }
            }

            result shouldBe "onCancel: Job was cancelled"
        }

        test("Execution with exception") {
            var result = ""

            coroutineScope {
                safeLaunch(
                    onCancel = { message ->
                        result = "onCancel: $message"
                    },
                    onException = { message ->
                        result = "onException: $message"
                    }
                ) {
                    delay(100)
                    error("Illegal state exception")
                }
            }

            result shouldBe "onException: Illegal state exception"
        }
    }
}
