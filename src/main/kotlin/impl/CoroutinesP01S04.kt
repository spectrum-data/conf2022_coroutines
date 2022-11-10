package impl

import kotlinx.coroutines.*

/**
 * Часть 1. Задание 4. Смена контекста.
 *
 * Вам нужно реализовать функцию, которая вызывает переданные ей лямбды в разных потоках.
 * Названия потоков должны соответствовать полученным значениям `thread1Name` и `thread2Name`:
 * 1. функция `prepare()` должна быть запущена в потоке с именем `thread1Name`
 * 2. функция `getQuery()` должна быть запущена в потоке с именем `thread2Name`
 * 3. функция `execute()` должна быть запущена в потоке с именем `thread1Name`
 * Вызов функций должен выполняться в следующем порядке:
 * 1. `prepare()` может выполняться параллельно с `getQuery()` и `execute()`
 * 2. `execute()` должна быть вызвана после `getQuery()`, и на вход получает результат выполнения `getQuery()`
 */
object CoroutinesP01S04 {
    suspend fun exec(
        thread1Name: String,
        thread2Name: String,
        prepare: suspend () -> Unit,
        getQuery: suspend () -> String,
        execute: suspend (query: String) -> Unit,
    ) {
        val context1 = newSingleThreadContext(name = thread1Name)
        val context2 = newSingleThreadContext(name = thread2Name)

        withContext(context2) {
            val getQueryJob = async {
                getQuery()
            }
            withContext(context1) {
                launch {
                    prepare()
                }
                launch {
                    getQueryJob
                        .await()
                        .also {
                            execute(it)
                        }
                }
            }
        }
    }
}
