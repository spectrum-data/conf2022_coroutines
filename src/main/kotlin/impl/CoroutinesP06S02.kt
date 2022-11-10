package impl

import kotlinx.coroutines.*

/**
 * Часть 6. Задание 2. Перехват исключений в дочерних корутинах.
 *
 * В этом задании вам необходимо обработать исключения, возникшие в дочерних корутинах.
 * Для обработки исключения используйте колбэк `onException`:
 * 1. Сообщение из первого возникшего исключения передайте в параметре `message`.
 * 2. Все остальные исключения - в списке `suppressed`.
 * Подсказка: попробуйте применить обработчик исключений (handler).
 */
object CoroutinesP06S02 {
    @OptIn(DelicateCoroutinesApi::class)
    suspend fun safeLaunchAndJoin(
        onException: (message: String, suppressed: List<String>) -> Unit,
        body: suspend CoroutineScope.() -> Job
    ) {
        // Реализация ->
        val handler = CoroutineExceptionHandler { _, exception ->
            onException(
                exception.message ?: "",
                exception.suppressed.map { it.message ?: "" }
            )
        }
        val job = GlobalScope.launch(handler) {
            body()
        }
        job.join()
        // <- Реализация
    }
}
