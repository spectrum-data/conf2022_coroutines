package impl

import kotlinx.coroutines.*

/**
 * Часть 1. Задание 5. Диспетчеры корутин.
 *
 * Функция `executeDataBaseRequest()` предназначена для выполнения параллельных запросов к базе данных
 * со степенью параллелизма `parallelism`.
 * Необходимо добиться того, чтобы как минимум 64 запроса могли выполняться одновременно
 * даже при блокировке потока внутри лямбды.
 */
object CoroutinesP01S05 {
    suspend fun executeDataBaseRequest(
        parallelism: Int,
        body: suspend () -> Unit
    ) {
        TODO("Not yet implemented")
    }
}
