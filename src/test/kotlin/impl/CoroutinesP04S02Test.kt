package impl

import impl.CoroutinesP04S02.filterPhones
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactly
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach
import service.ServiceP04S02

class CoroutinesP04S02Test : FunSpec() {
    init {
        test("Receive correct phones list") {
            val sourceList = listOf(
                "",
                "+79085684397",
                "89074685219",
                "9037897956",
                "(902) 951-85-23",
                "+73439067842",
                "8-800-600-30-50",
                "+97508765214",
                "+79514568213, тел. 904 231-75-28, не указан, ",
                "\t, 89637418523; 9074568529, 3418649173",
                "9086549872",
            )
            val expectedResult = listOf(
                "9085684397",
                "9074685219",
                "9037897956",
                "9029518523",
                "9514568213",
                "9042317528",
                "9637418523",
                "9074568529",
            )
            val result = mutableListOf<String>()

            val writer = ServiceP04S02.Writer { item ->
                result.add(item)
            }

            filterPhones(sourceList.asFlow(), writer, 8)

            result shouldContainExactly expectedResult
        }

        test("Emit and collect asynchronously") {
            val sourceList = listOf(
                "",
                "+79085684397",
                "89074685219",
                "9037897956",
                "(902) 951-85-23",
                "+73439067842",
                "8-800-600-30-50",
                "+97508765214",
                "+79514568213, тел. 904 231-75-28, не указан, ",
                "\t, 89637418523; 9074568529, 3418649173",
                "9086549872",
            )
            val expectedResult = listOf(
                "",
                "+79085684397",
                "9085684397",
                "89074685219",
                "9074685219",
                "9037897956",
                "9037897956",
                "(902) 951-85-23",
                "9029518523",
                "+73439067842",
                "8-800-600-30-50",
                "+97508765214",
                "+79514568213, тел. 904 231-75-28, не указан, ",
                "9514568213",
                "9042317528",
                "\t, 89637418523; 9074568529, 3418649173",
                "9637418523",
                "9074568529",
            )
            val result = mutableListOf<String>()

            val flow = sourceList.asFlow().onEach { item ->
                result.add(item)
            }

            val writer = ServiceP04S02.Writer { item ->
                result.add(item)
            }

            filterPhones(flow, writer, 8)

            result shouldContainExactly expectedResult
        }
    }
}
