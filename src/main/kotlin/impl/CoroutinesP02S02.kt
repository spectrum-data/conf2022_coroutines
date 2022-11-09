package impl

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Часть 2. Задание 2. Синхронизация доступа.
 *
 * Теперь вы имеете возможность реализовать собственный потокобезопасный счетчик.
 * Какой класс лучше использовать?
 */
object CoroutinesP02S02 {
    suspend fun executeAndSum(times: Int, body: suspend (i: Int) -> Int): Int {
        // Реализация ->
        var counter = 0
        coroutineScope {
            repeat(times) { i ->
                launch {
                    counter += body(i)
                }
            }
        }
        return counter
        // <- Реализация
    }
}
