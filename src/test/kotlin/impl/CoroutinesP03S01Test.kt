package impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.longs.shouldBeLessThan
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.delay
import kotlin.random.Random
import kotlin.system.measureTimeMillis

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesP03S01Test : FunSpec() {
    init {
        test("Receive correct list") {
            val items = (1..100).map { Random.nextInt(1,10) }
            val expectedItems = items.map { "value: $it" }
            val channel = produce {
                items.forEach { send(it) }
            }

            val result = CoroutinesP03S01.channelToList(channel) {
                "value: $it"
            }

            result shouldContainExactly expectedItems
        }
    }
}
