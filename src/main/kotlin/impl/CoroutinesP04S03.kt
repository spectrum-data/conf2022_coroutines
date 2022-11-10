package impl

import kotlinx.coroutines.flow.*
import service.ServiceP04S03

/**
 * Часть 4. Задание 3. Объединение потоков.
 *
 * Вы получаете на вход три потока: идентификаторы, имена и телефоны.
 * Объедините их в один поток таким образом, чтобы каждый выходной элемент содержал строку для записи в CSV-файл.
 * Заголовок у CSV такой: `id,name,phone`
 */
object CoroutinesP04S03 {
    suspend fun writeLeads(
        idFlow: Flow<Int>,
        nameFlow: Flow<String>,
        phoneFlow: Flow<String>,
        writer: ServiceP04S03.Writer
    ) {
        // Реализация ->
        idFlow.zip(nameFlow) { id, name ->
            Pair(id, name)
        }.zip(phoneFlow) { (id, name), phone ->
            "$id,$name,$phone"
        }.collect { row ->
            writer.write(row)
        }
        // <- Реализация
    }
}
