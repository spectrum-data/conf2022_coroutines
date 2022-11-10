package impl

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import service.ServiceP01S01

/**
 * Часть 1. Задание 1. Параллельные вызовы (launch).
 *
 * Вам нужно записать в базу данных все строки, полученные на вход функции saveToRepository().
 * СУБД оптимизирована под параллельную запись, но сохранение каждой записи занимает некоторое время.
 * Используйте метод `repository.save()` для сохранения записи.
 */
class CoroutinesP01S01(
    private val repository: ServiceP01S01.Repository
) {
    fun saveToRepository(rows: List<String>) {
        runBlocking {
            rows.forEach {
                launch {
                    repository.save(row = it)
                }
            }
        }
    }
}
