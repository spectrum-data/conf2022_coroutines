package impl

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import service.ServiceP03S03

/**
 * Часть 3. Задание 3. Параллельный доступ к каналам.
 *
 * В этом задании вам нужно прочитать записи из одной БД и записать их в другую.
 * Чтение и запись в каналы можно производить в параллельном режиме.
 * Степень параллелизма задается параметрами `readParallelism` (для чтения) и `writeParallelism` (для записи).
 */
object CoroutinesP03S03 {
    suspend fun pumpDataBase(
        readParallelism: Int,
        writeParallelism: Int,
        reader: ServiceP03S03.DataBaseReader,
        writer: ServiceP03S03.DataBaseWriter
    ) {
        TODO("Not yet implemented")
    }
}
