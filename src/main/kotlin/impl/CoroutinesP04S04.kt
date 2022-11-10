package impl

import kotlinx.coroutines.flow.*
import service.ServiceP04S04
import java.security.MessageDigest

/**
 * Часть 4. Задание 4. Комбинация потоков.
 *
 * Вы получаете два потока, в которых передаются значения с разных датчиков: температуры и влажности воздуха.
 * Необходимо скомбинировать два этих потока таким образом, чтобы при появлении нового значения в любом из них
 * происходила отрисовка обоих значений в интерфейсе пользователя (функция `renderer.render()`).
 * Обратите внимание, что датчики работают нестабильно и иногда могут отдавать значения `null` - такие значения отрисовывать не нужно.
 */
object CoroutinesP04S04 {
    suspend fun renderMeasurements(
        temperatureFlow: Flow<Double?>,
        humidityFlow: Flow<Int?>,
        renderer: ServiceP04S04.Renderer
    ) {
        temperatureFlow.filterNotNull().combine(humidityFlow.filterNotNull()){ t, h ->
            ServiceP04S04.Measurements(t, h)
        }.collect { m ->
            renderer.render(m)
        }
    }
}
