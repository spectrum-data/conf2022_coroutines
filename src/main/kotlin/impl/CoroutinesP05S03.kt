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
        // Реализация ->
        return buildList {
            repeat(count) {
                select {
                    deferredList.filter { !it.isCompleted }.forEach { deferred ->
                        deferred.onAwait { message ->
                            add(message)
                        }
                    }
                }
            }
        }
        // <- Реализация
    }
}
