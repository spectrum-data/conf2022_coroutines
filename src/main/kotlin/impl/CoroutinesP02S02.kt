package impl

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

/**
 * Часть 2. Задание 2. Синхронизация доступа.
 *
 * Теперь вы имеете возможность реализовать собственный потокобезопасный счетчик.
 * Какой класс лучше использовать?
 */
object CoroutinesP02S02 {
    suspend fun executeAndSum(times: Int, body: suspend (i: Int) -> Int): Int {
        // Реализация ->
        val counter = AtomicInteger()
        coroutineScope {
            repeat(times) { i ->
                launch {
                    counter.addAndGet(body(i))
                }
            }
        }
        return counter.get()
        // <- Реализация
    }
}
