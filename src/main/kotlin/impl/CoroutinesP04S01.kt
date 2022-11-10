package impl

import kotlinx.coroutines.flow.flow
import service.ServiceP04S01

/**
 * Часть 4. Задание 1. Создание асинхронного потока.
 *
 * Теперь займемся асинхронными потоками.
 * Для начала попробуйте сформировать асинхронный поток из "итератора", который имеет методы `hasNext()` и `next()`
 * Полученный поток нужно передать в метод `writeFromFlow()` райтера.
 */
object CoroutinesP04S01 {
    suspend fun processItemsList(
        reader: ServiceP04S01.Reader,
        writer: ServiceP04S01.Writer
    ) {
        // Реализация ->
        val flow = flow {
            while (reader.hasNext()) {
                emit(reader.next())
            }
        }
        writer.writeFromFlow(flow)
        // <- Реализация
    }
}
