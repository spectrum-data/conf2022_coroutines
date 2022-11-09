package impl

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.longs.shouldBeLessThan
import service.ServiceP01S01
import kotlin.system.measureTimeMillis

class CoroutinesP01S01Test : FunSpec() {
    init {
        context("Save all received rows") {
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
            ).forEach { rows ->
                test("List of ${rows.size} rows") {
                    val repository = ServiceP01S01.Repository()
                    CoroutinesP01S01(repository).saveToRepository(rows)
                    repository.rows shouldContainExactlyInAnyOrder rows
                }
            }
        }

        context("Save time is less than ...") {
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
            ).forEach { (rows, expectedTime) ->
                test("Save time for ${rows.size} rows less than $expectedTime ms") {
                    val repository = ServiceP01S01.Repository()
                    val time = measureTimeMillis {
                        CoroutinesP01S01(repository).saveToRepository(rows)
                    }
                    time shouldBeLessThan expectedTime
                }
            }
        }
    }
}
