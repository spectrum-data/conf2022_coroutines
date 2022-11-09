package impl

import impl.CoroutinesP05S01.logMessages
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesP05S01Test : FunSpec() {
    init {
        test("Receive all items in right order") {
            val messages1 = mapOf(
                "Channel 1 message 1" to 100L,
                "Channel 1 message 2" to 100L,
                "Channel 1 message 3" to 100L,
                "Channel 1 message 4" to 100L,
                "Channel 1 message 5" to 100L,
            )
            val messages2 = mapOf(
                "Channel 2 message 1" to 150L,
                "Channel 2 message 2" to 100L,
                "Channel 2 message 3" to 10L,
                "Channel 2 message 4" to 90L,
                "Channel 2 message 5" to 300L,
            )
            val expectedResult = listOf(
                "Channel 1 message 1",
                "Channel 2 message 1",
                "Channel 1 message 2",
                "Channel 2 message 2",
                "Channel 2 message 3",
                "Channel 1 message 3",
                "Channel 2 message 4",
                "Channel 1 message 4",
                "Channel 1 message 5",
            )
            val result = mutableListOf<String>()

            withContext(Dispatchers.Default) {
                val channel1 = produce(capacity = 5) {
                    messages1.forEach { (message, time) ->
                        delay(time)
                        send(message)
                    }
                }
                val channel2 = produce(capacity = 5) {
                    messages2.forEach { (message, time) ->
                        delay(time)
                        send(message)
                    }
                }

                val logChannel = logMessages(channel1, channel2)

                logChannel.consumeEach {
                    result.add(it)
                }
            }

            result shouldContainExactly expectedResult
        }
    }
}
