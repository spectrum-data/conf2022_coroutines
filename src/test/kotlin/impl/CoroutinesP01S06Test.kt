package impl

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.string.shouldStartWith
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext
import service.ServiceP01S06
import kotlin.random.Random

@OptIn(DelicateCoroutinesApi::class)
class CoroutinesP01S06Test : FunSpec() {
    init {
        test("Switch thread after execute") {
            val threadBeforeName = "Test thread name before ${Random.nextInt()}"
            val threadAfterName = "Test thread name after ${Random.nextInt()}"
            val log = ServiceP01S06.Log()

            newSingleThreadContext(threadBeforeName).use { contextBefore ->
                newSingleThreadContext(threadAfterName).use { contextAfter ->
                    withContext(contextBefore) {
                        CoroutinesP01S06.execute(log) {
                            withContext(contextAfter) {
                                delay(100)
                            }
                        }
                    }
                }
            }

            assertSoftly {
                log.threadBefore shouldStartWith threadBeforeName
                log.threadAfter shouldStartWith threadAfterName
            }
        }
    }
}
