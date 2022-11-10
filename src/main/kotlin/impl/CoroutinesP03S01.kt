package impl

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach

/**
 * Часть 3. Задание 1. Получение элементов из канала.
 *
 * Попробуйте написать свою собственную функцию преобразования канала в список.
 * Каждый элемент должен быть преобразован в строку с помощью функции `body()`.
 */
object CoroutinesP03S01 {
    suspend fun channelToList(
        channel: ReceiveChannel<Int>,
        body: suspend (Int) -> String
    ): List<String> {
        return buildList {
            channel.consumeEach {
                add(element = body(it))
            }
        }
    }
}
