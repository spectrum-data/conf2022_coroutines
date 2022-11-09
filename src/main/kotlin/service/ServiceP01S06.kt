package service

sealed class ServiceP01S06 {
    data class Log(
        var threadBefore: String = "",
        var threadAfter: String = ""
    ) {
        fun logBefore() {
            threadBefore = Thread.currentThread().name
        }

        fun logAfter() {
            threadAfter = Thread.currentThread().name
        }
    }
}
