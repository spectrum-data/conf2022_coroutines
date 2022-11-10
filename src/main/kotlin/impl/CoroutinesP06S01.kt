package impl

import kotlinx.coroutines.*

/**
 * Часть 6. Задание 1. Перехват исключений при отмене корутин.
 *
 * Реализуйте функцию "безопасного" запуска корутины, которая будет вызывать колбеки:
 * `onCancel` - в случае отмены корутины
 * `onException` - в случае возникновения исключения внутри корутины
 */
object CoroutinesP06S01 {
    fun CoroutineScope.safeLaunch(
        onCancel: (message: String) -> Unit = {},
        onException: (message: String) -> Unit = {},
        body: suspend () -> Unit
    ): Job {
        return launch {
            try{
                body()
            }catch (e: CancellationException) {
                onCancel(e.message ?: "")
            }catch (e: Throwable){
                onException(e.message ?: "")
            }
        }
    }
}
