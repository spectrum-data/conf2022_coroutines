package service

sealed class ServiceP04S02 {
    class Writer(
        private val body: suspend (item: String) -> Unit
    ) {
        suspend fun write(phone: String) = body(phone)
    }
}
