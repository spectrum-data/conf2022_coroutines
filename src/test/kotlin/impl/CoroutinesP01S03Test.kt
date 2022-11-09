package impl

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.delay
import kotlin.system.measureTimeMillis

class CoroutinesP01S03Test : FunSpec() {
    init {
        context("Receive correct exec() result") {
            mapOf(
                100L to "100 ms - is OK",
                500L to "500 ms - is OK",
                900L to "900 ms - is OK",
                1000L to "Too long body execution",
                2000L to "Too long body execution",
            ).forEach { (delayTime, expectedResult) ->
                test("Delay for $delayTime ms -> '$expectedResult'") {
                    shouldNotThrowAny {
                        val result = CoroutinesP01S03.exec {
                            delay(delayTime)
                            "$delayTime ms - is OK"
                        }
                        result shouldBe expectedResult
                    }
                }
            }
        }

        context("Execute time is less than 1000 ms") {
            listOf(
                100L,
                500L,
                900L,
                1000L,
                2000L,
                3000L,
            ).forEach { delayTime ->
                test("Delay for $delayTime ms") {
                    val time = measureTimeMillis {
                        shouldNotThrowAny {
                            CoroutinesP01S03.exec {
                                delay(delayTime)
                                "$delayTime ms - is OK"
                            }
                        }
                    }
                    time shouldBeLessThan 1100
                }
            }
        }
    }
}
