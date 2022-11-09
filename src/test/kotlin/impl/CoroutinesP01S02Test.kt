package impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.longs.shouldBeLessThan
import service.ServiceP01S01
import service.ServiceP01S02
import kotlin.system.measureTimeMillis

class CoroutinesP01S02Test : FunSpec() {
    init {
        context("Read all saved rows") {
            listOf(
                listOf(
                    "How doth the little crocodile",
                ),
                listOf(
                    "How doth the little crocodile",
                    "Improve his shining tail,",
                ),
                listOf(
                    "How doth the little crocodile",
                    "Improve his shining tail,",
                    "And pour the waters of the Nile",
                    "On every golden scale!,",
                    "How cheerfully he seems to grin,",
                    "How neatly spread his claws,",
                    "And welcome little fishes in",
                    "With gently smiling jaws!",
                ),
            ).forEach { expectedRows ->
                test("List of ${expectedRows.size} rows") {
                    val repository = ServiceP01S02.Repository(expectedRows)
                    val rows = CoroutinesP01S02(repository).readFromRepository()
                    rows shouldContainExactlyInAnyOrder expectedRows
                }
            }
        }

        context("Read time is less than ...") {
            mapOf(
                listOf(
                    "How doth the little crocodile",
                ) to 200L,
                listOf(
                    "How doth the little crocodile",
                    "Improve his shining tail,",
                ) to 200L,
                listOf(
                    "How doth the little crocodile",
                    "Improve his shining tail,",
                    "And pour the waters of the Nile",
                    "On every golden scale!,",
                    "How cheerfully he seems to grin,",
                    "How neatly spread his claws,",
                    "And welcome little fishes in",
                    "With gently smiling jaws!",
                ) to 200L,
            ).forEach { (expectedRows, expectedTime) ->
                test("Save time for ${expectedRows.size} rows less than $expectedTime ms") {
                    val repository = ServiceP01S02.Repository(expectedRows)
                    val time = measureTimeMillis {
                        CoroutinesP01S02(repository).readFromRepository()
                    }
                    time shouldBeLessThan expectedTime
                }
            }
        }
    }
}
