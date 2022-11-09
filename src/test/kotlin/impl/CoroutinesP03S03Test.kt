package impl

import impl.CoroutinesP03S02.listToChannel
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.longs.shouldBeGreaterThan
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.longs.shouldBeLessThan
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import service.ServiceP03S03
import java.util.Collections
import kotlin.random.Random
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class CoroutinesP03S03Test : FunSpec() {
    init {
        test("Read and write all items with parallelism 64") {
            val items = (1..100).map { Random.nextInt(1,10) }
            val result = Collections.synchronizedList(mutableListOf<ServiceP03S03.Record>())
            val reader = ServiceP03S03.DataBaseReader(items.size) { i ->
                delay(100)
                ServiceP03S03.Record(id = items[i])
            }
            val writer = ServiceP03S03.DataBaseWriter { record ->
                delay(100)
                result.add(record)
            }
            val expectedItems = items.map { ServiceP03S03.Record(id = it) }

            CoroutinesP03S03.pumpDataBase(64, 64, reader, writer)

            result shouldContainExactlyInAnyOrder expectedItems
        }

        context("Execution time is less than ...") {
            mapOf(
                4 to 4000L,
                8 to 3000L,
                16 to 2000L,
                32 to 1000L,
                64 to 500L,
            ).forEach { (parallelism, expectedTime) ->
                test("For parallelism $parallelism time is less than $expectedTime ms") {
                    val items = (1..128).map { it }
                    val reader = ServiceP03S03.DataBaseReader(items.size) { i ->
                        delay(100)
                        ServiceP03S03.Record(id = items[i])
                    }
                    val writer = ServiceP03S03.DataBaseWriter {
                        delay(100)
                    }

                    val time = measureTimeMillis {
                        CoroutinesP03S03.pumpDataBase(parallelism, parallelism, reader, writer)
                    }

                    time shouldBeLessThan expectedTime
                }
            }
        }

        test("Read parallelism only for reading") {
            val items = (1..4).map { it }
            val reader = ServiceP03S03.DataBaseReader(items.size) { i ->
                delay(500)
                ServiceP03S03.Record(id = items[i])
            }
            val writer = ServiceP03S03.DataBaseWriter {}

            val time = measureTimeMillis {
                CoroutinesP03S03.pumpDataBase(1, 64, reader, writer)
            }

            time shouldBeGreaterThanOrEqual 2000
        }

        test("Write parallelism only for writing") {
            val items = (1..4).map { it }
            val reader = ServiceP03S03.DataBaseReader(items.size) { i ->
                ServiceP03S03.Record(id = items[i])
            }
            val writer = ServiceP03S03.DataBaseWriter {
                delay(500)
            }

            val time = measureTimeMillis {
                CoroutinesP03S03.pumpDataBase(64, 1, reader, writer)
            }

            time shouldBeGreaterThanOrEqual 2000
        }
    }
}
