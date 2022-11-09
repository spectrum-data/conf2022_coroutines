package service

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow

sealed class ServiceP04S01 {
    class Reader(
        private val delayTime: Long,
        items: List<String>
    ) {
        private val iterator = items.iterator()

        fun hasNext() = iterator.hasNext()

        suspend fun next(): String {
            delay(delayTime)
            return iterator.next()
        }
    }

    class Writer(
        private val body: suspend (item: String) -> Unit
    ) {
        suspend fun writeFromFlow(flow: Flow<String>) {
            flow.collect {
                body(it)
            }
        }
    }
}
