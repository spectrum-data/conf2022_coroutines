package impl

import impl.CoroutinesP04S04.renderMeasurements
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import service.ServiceP04S04

class CoroutinesP04S04Test : FunSpec() {
    init {
        test("Render correct sequence of measurements") {
            val temperatureFlow = flowOf(
                23.5,
                23.2,
                23.8,
                22.9,
                null,
                23.7,
            ).onEach { delay(110) }
            val humidityFlow = flowOf(
                45,
                46,
                null,
                43,
                41,
            ).onEach { delay(150) }
            val expectedResult = listOf(
                ServiceP04S04.Measurements(temperature = 23.5, humidity = 45),
                ServiceP04S04.Measurements(temperature = 23.2, humidity = 45),
                ServiceP04S04.Measurements(temperature = 23.2, humidity = 46),
                ServiceP04S04.Measurements(temperature = 23.8, humidity = 46),
                ServiceP04S04.Measurements(temperature = 22.9, humidity = 46),
                ServiceP04S04.Measurements(temperature = 22.9, humidity = 43),
                ServiceP04S04.Measurements(temperature = 23.7, humidity = 43),
                ServiceP04S04.Measurements(temperature = 23.7, humidity = 41),
            )
            val result = mutableListOf<ServiceP04S04.Measurements>()

            val renderer = ServiceP04S04.Renderer { item ->
                result.add(item)
            }

            renderMeasurements(temperatureFlow, humidityFlow, renderer)

            result shouldContainExactly expectedResult
        }
    }
}
