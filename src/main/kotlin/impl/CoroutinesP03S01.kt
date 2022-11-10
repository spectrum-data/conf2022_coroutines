package impl

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.flow.consumeAsFlow
import kotlin.random.Random

/**
 * Часть 3. Задание 1. Получение элементов из канала.
 *
 * Попробуйте написать свою собственную функцию преобразования канала в список.
 * Каждый элемент должен быть преобразован в строку с помощью функции `body()`.
 */
object CoroutinesP03S01 {
    suspend fun channelToList(
        channel: ReceiveChannel<Int>,
        body: suspend (Int) -> String
    ): List<String> {
        val result = mutableListOf<String>()
        while (true) {
            val next = kotlin.runCatching {  channel.receive() }
            if(next.isFailure){
                break
            }
            result.add(body(next.getOrThrow()))
        }
        return result
        /*
        return coroutineScope {
            val stringChannel = produce {
                for(i in channel){
                    launch {
                        delay(
                            Random.nextLong(10)
                        )
                        send(body(i))
                    }
                }
            }
            val result = mutableListOf<String>()
            for( s in stringChannel ){
                result.add(s)
            }
            result
        }

         */
    }
}
