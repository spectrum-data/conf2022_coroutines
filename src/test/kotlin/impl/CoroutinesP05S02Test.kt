package impl

import impl.CoroutinesP05S02.processMessages
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutinesP05S02Test : FunSpec() {
    init {
        test("Receive all items in right order") {
            val messages = listOf(
                "Message 1",
                "Message 2",
                "Message 3",
                "Message 4",
                "Message 5",
                "Message 6",
            )
            val processExpectedResult = listOf(
                "Message 1",
                "Message 4",
            )
            val logExpectedResult = listOf(
                "Message 2",
                "Message 3",
                "Message 5",
                "Message 6",
            )
            val processResult = mutableListOf<String>()
            val logResult = mutableListOf<String>()

            val messageChannel = produce {
                messages.forEach {
                    delay(100)
                    send(it)
                }
            }
            val processChannel = Channel<String>()
            val logChannel = Channel<String>()

            coroutineScope {
                launch {
                    processChannel.consumeEach {
                        delay(250)
                        processResult.add(it)
                    }
                }

                launch {
                    logChannel.consumeEach {
                        logResult.add(it)
                    }
                }

                processMessages(messageChannel, processChannel, logChannel)
            }

            assertSoftly {
                processResult shouldContainExactly processExpectedResult
                logResult shouldContainExactly logExpectedResult
            }
        }

        test("Channels are closed after processing messages") {
            val messageChannel = produce { send("") }
            val processChannel = Channel<String>()
            val logChannel = Channel<String>()

            launch {
                processChannel.consumeEach {}
            }

            launch {
                logChannel.consumeEach {}
            }

            processMessages(messageChannel, processChannel, logChannel)

            assertSoftly {
                processChannel.isClosedForSend.shouldBeTrue()
                logChannel.isClosedForSend.shouldBeTrue()
            }
        }
    }
}
