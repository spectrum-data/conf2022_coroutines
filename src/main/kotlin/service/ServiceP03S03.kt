package service

import java.util.concurrent.atomic.AtomicInteger

sealed class ServiceP03S03 {
    data class Record(
        val id: Int
    )

    class DataBaseReader(
        private val limit: Int,
        private val body: suspend (i: Int) -> Record
    ) {
        private var counter = AtomicInteger(0)

        fun hasNextRecord(): Boolean = counter.get() < limit

        suspend fun readNextRecordOrNull(): Record? = if (hasNextRecord()) body(counter.getAndIncrement()) else null
    }

    class DataBaseWriter(
        private val body: suspend (record: Record) -> Unit
    ) {
        suspend fun write(record: Record) = body(record)
    }
}
