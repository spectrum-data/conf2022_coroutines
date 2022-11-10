package impl

import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

/**
 * Часть 1. Задание 3. Таймаут корутины.
 *
 * Вам нужно вызвать функцию `body()`, переданную как параметр в `exec()`, но с одним условием:
 * выполнение `body()` не должно занимать более 1 секунды.
 * Если функция `body()` выполняется дольше, нужно вернуть строку "Too long body execution",
 * не дожидаясь окончания выполнения.
 */
object CoroutinesP01S03 {
    private const val TIMEOUT_MS = 1000L
    private const val TIMEOUT_MESSAGE = "Too long body execution"

    suspend fun exec(body: suspend () -> String): String {
        return withTimeoutOrNull(TIMEOUT_MS){
            body()
        } ?: TIMEOUT_MESSAGE
    }
}
