package service

import kotlinx.coroutines.delay
import java.util.*

sealed class ServiceP01S01 {
    class Repository {
        val rows: MutableList<String> = Collections.synchronizedList(mutableListOf<String>())

        suspend fun save(row: String) {
            delay(100)
            rows.add(row)
        }
    }
}
