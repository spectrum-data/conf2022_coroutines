package impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.selects.select

/**
 * Часть 5. Задание 1. Выражение Select, объединение каналов.
 *
 * У вас есть два канала с сообщениями.
 * Требуется вернуть новый канал для логирования и отправлять в него сообщения из обоих каналов при их получении.
 * Логировать сообщения нужно до тех пор, пока один из входящих каналов не будет закрыт.
 * Подсказка: используйте выражение `select` для выполнения этого задания. 
 */
object CoroutinesP05S01 {
    fun CoroutineScope.logMessages(
        messageChannel1: ReceiveChannel<String>,
        messageChannel2: ReceiveChannel<String>
    ): ReceiveChannel<String> {
        TODO("Not yet implemented")
    }
}
