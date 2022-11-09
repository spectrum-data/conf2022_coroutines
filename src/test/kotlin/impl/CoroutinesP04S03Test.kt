package impl

import impl.CoroutinesP04S03.writeLeads
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.flow.flowOf
import service.ServiceP04S03

class CoroutinesP04S03Test : FunSpec() {
    init {
        test("Receive correct leads list") {
            val idFlow = flowOf(
                1,
                2,
                3,
                4,
            )
            val nameFlow = flowOf(
                "Alice",
                "White Rabbit",
                "Hatter",
                "Cheshire cat",
            )
            val phoneFlow = flowOf(
                "9085684397",
                "9074685219",
                "9037897956",
                "9029518523",
            )
            val expectedResult = listOf(
                "1,Alice,9085684397",
                "2,White Rabbit,9074685219",
                "3,Hatter,9037897956",
                "4,Cheshire cat,9029518523",
            )
            val result = mutableListOf<String>()

            val writer = ServiceP04S03.Writer { item ->
                result.add(item)
            }

            writeLeads(idFlow, nameFlow, phoneFlow, writer)

            result shouldContainExactly expectedResult
        }
    }
}
