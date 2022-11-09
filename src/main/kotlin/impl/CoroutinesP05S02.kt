package impl

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.selects.select

/**
 * Часть 5. Задание 2. Выражение Select, отправка сообщений в запасной канал.
 *
 * В этом задании вам нужно обработать все сообщения из канала `messageChannel`
 * и отправить их в канал `processChannel` или `logChannel`.
 * При этом основной канал `processChannel` обрабатывает сообщения довольно медленно,
 * поэтому все сообщения, полученные, пока `processChannel` занят, нужно отправлять в `logChannel`.
 */
object CoroutinesP05S02 {
    suspend fun processMessages(
        messageChannel: ReceiveChannel<String>,
        processChannel: SendChannel<String>,
        logChannel: SendChannel<String>
    ) {
        TODO("Not yet implemented")
    }
}
