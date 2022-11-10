package impl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.channels.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import service.ServiceP01S02

/**
 * Часть 1. Задание 2. Параллельное получение результатов (async).
 *
 * Вам нужно прочитать из репозитория (базы данных) все строки.
 * СУБД оптимизирована под параллельное чтение, но чтение каждой записи занимает некоторое время.
 * Используйте свойство `repository.rowCount` для получения количества записей в репозитории.
 * Используйте метод `repository.read()` для получения записи по индексу.
 */
class CoroutinesP01S02(
    private val repository: ServiceP01S02.Repository
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun readFromRepository(): List<String> {
        return runBlocking {
            produce {
                for (i in 0..repository.rowCount - 1) {
                    launch {
                        send(repository.read(i))
                    }
                }
            }.toList()
        }
    }
}
