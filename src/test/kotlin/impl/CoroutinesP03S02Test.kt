package impl

import impl.CoroutinesP03S02.listToChannel
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.coroutineScope
import kotlin.random.Random

class CoroutinesP03S02Test : FunSpec() {
    init {
        test("Receive correct elements from channel") {
            val items = (1..100).map { Random.nextInt(1,10) }
            val expectedItems = items.map { "value: $it" }

            val result = coroutineScope {
                listToChannel(items) {
                    "value: $it"
                }.toList()
            }

            result shouldContainExactly expectedItems
        }
    }
}
