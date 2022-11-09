package service

sealed class ServiceP04S04 {
    data class Measurements(
        val temperature: Double,
        val humidity: Int
    )

    class Renderer(
        private val body: suspend (measurements: Measurements) -> Unit
    ) {
        suspend fun render(measurements: Measurements) = body(measurements)
    }
}
