package impl

import kotlinx.coroutines.*
import service.ServiceP02S01

/**
 * Часть 2. Задание 1. Синхронизация доступа.
 *
 * У вас есть класс `Counter`, который не является потокобезопасным.
 * Необходимо модифицировать функцию таким образом, чтобы вызовы `body(i)` остались параллельными,
 * и при этом `counter` возвращал бы корректный результат.
 */
object CoroutinesP02S01 {
    @OptIn(DelicateCoroutinesApi::class)
    suspend fun executeAndSum(
        counter: ServiceP02S01.Counter,
        times: Int,
        body: suspend (i: Int) -> Int
    ) {
        // Реализация ->
        newSingleThreadContext("Single thread context").use { context ->
            withContext(context) {
                repeat(times) { i ->
                    launch {
                        counter.add(body(i))
                    }
                }
            }
        }
        // <- Реализация
    }
}
