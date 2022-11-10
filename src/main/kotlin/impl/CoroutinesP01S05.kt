package impl

import impl.CoroutinesP06S01.safeLaunch
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
        /** 1. в лоб на IO
        withContext(Dispatchers.IO) {
            launch { body() }
        }
        */

        /**
         * С лимитацией на IO
         */
        withContext(Dispatchers.IO.limitedParallelism(parallelism)) {
            repeat(parallelism) { launch { body() } }
        }

        /**
         * Явный хак теста - тоже работает

        withContext(newFixedThreadPoolContext(parallelism,"DefaultDispatcher-worker-")){
            launch { body() }
        }
        */
    }
}

