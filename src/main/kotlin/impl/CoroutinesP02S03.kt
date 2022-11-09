package impl

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

/**
 * Часть 2. Задание 3. Синхронизация доступа.
 *
 * А что делать, если вам требуется не просто увеличивать значение счетчика,
 * а выполнять более сложные преобразования общего ресурса - например, собирать строку?
 */
object CoroutinesP02S03 {
    suspend fun executeAndConcatenate(times: Int, body: suspend (i: Int) -> String): String {
        var accumulator = ""
        coroutineScope {
            repeat(times) { i ->
                launch {
                    val value = body(i)
                        accumulator += value
                }
            }
        }
        return accumulator
    }
}
