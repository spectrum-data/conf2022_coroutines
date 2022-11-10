package impl

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.selects.select

/**
 * Часть 5. Задание 3. Выражение Select, выбор первых результатов.
 *
 * Вы получаете список deferred-объектов.
 * Нужно вернуть первые `count` результатов из тех корутин, которые завершат свое выполнение первыми.
 * Дожидаться выполнения оставшихся корутин при этом не нужно!
 */
object CoroutinesP05S03 {
    suspend fun takeFirstMessages(
        deferredList: List<Deferred<String>>,
        count: Int
    ): List<String> {
        val result = mutableListOf<String>()
        repeat(count) {
            select {
                deferredList.forEach {
                    it.onAwait { result.add(it) }
                }
            }
        }
        return result
    }

}
