package impl

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.longs.shouldBeLessThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class CoroutinesP01S04Test : FunSpec() {
    init {
        test("Run body in different context") {
            val thread1Name = "Test thread 1 name ${Random.nextInt()}"
            val thread2Name = "Test thread 2 name ${Random.nextInt()}"

            var firstBodyThreadName = ""
            var firstBodyThreadId = 0L
            var secondBodyThreadName = ""
            var secondBodyThreadId = 0L
            var thirdBodyThreadName = ""
            var thirdBodyThreadId = 0L

            CoroutinesP01S04.exec(
                thread1Name = thread1Name,
                thread2Name = thread2Name,
                prepare = {
                    firstBodyThreadName = Thread.currentThread().name
                    firstBodyThreadId = Thread.currentThread().id
                },
                getQuery = {
                    secondBodyThreadName = Thread.currentThread().name
                    secondBodyThreadId = Thread.currentThread().id
                    ""
                },
                execute = {
                    thirdBodyThreadName = Thread.currentThread().name
                    thirdBodyThreadId = Thread.currentThread().id
                }
            )

            assertSoftly {
                firstBodyThreadName shouldStartWith thread1Name
                secondBodyThreadName shouldStartWith thread2Name
                thirdBodyThreadName shouldStartWith thread1Name
                secondBodyThreadId shouldNotBe firstBodyThreadId
                thirdBodyThreadId shouldBe firstBodyThreadId
            }
        }

        test("Run time is less than 2500 ms") {
            val time = measureTimeMillis {
                CoroutinesP01S04.exec(
                    thread1Name = "Thread 1",
                    thread2Name = "Thread 2",
                    prepare = {
                        delay(1500)
                    },
                    getQuery = {
                        delay(1000)
                        ""
                    },
                    execute = {
                        delay(1000)
                    }
                )
            }

            time shouldBeLessThan 2500
        }

        test("Pass query from getQuery() to execute()") {
            val query = "Test query ${Random.nextInt()}"

            var thirdBodyThreadQuery = ""

            CoroutinesP01S04.exec(
                thread1Name = "Thread 1",
                thread2Name = "Thread 2",
                prepare = {},
                getQuery = {
                    query
                },
                execute = { query ->
                    thirdBodyThreadQuery = query
                }
            )

            thirdBodyThreadQuery shouldBe query
        }
    }
}
