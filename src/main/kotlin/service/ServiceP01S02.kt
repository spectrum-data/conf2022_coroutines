package service

import kotlinx.coroutines.delay
import java.util.*

sealed class ServiceP01S02 {
    class Repository(
        private val rows: List<String> = emptyList()
    ) {
        val rowCount: Int get() = rows.size

        suspend fun read(index: Int): String {
            delay(100)
            if (index < 0) error("Index should be greater than 0")
            if (index > rows.lastIndex) error("Index should be less or equal than $rowCount")
            return rows[index]
        }
    }
}
