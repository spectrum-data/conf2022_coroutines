package impl

import impl.CoroutinesP04S01.processItemsList
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import kotlinx.coroutines.delay
import service.ServiceP04S01

class CoroutinesP04S01Test : FunSpec() {
    init {
        test("Generate flow from async iterator") {
            val list = listOf(
                "They told me you had been to her,",
                "And mentioned me to him:",
                "She gave me a good character,",
                "But said I could not swim.",
                "He sent them word I had not gone",
                "\"(We know it to be true):\"",
                "If she should push the matter on,",
                "What would become of you?",
                "I gave her one, they gave him two,",
                "You gave us three or more;",
                "They all returned from him to you,",
                "Though they were mine before.",
            )
            val result = mutableListOf<String>()

            val reader = ServiceP04S01.Reader(10, list)
            val writer = ServiceP04S01.Writer { item ->
                delay(10)
                result.add(item)
            }

            processItemsList(reader, writer)

            result shouldContainExactlyInAnyOrder list
        }
    }
}
