package tasks.task_6_10_ObserverMut.auxiliaryClasses

import tasks.task_6_10_ObserverMut.DataRepository
import kotlin.concurrent.thread
import kotlin.random.Random
//
/**
 * КЛАСС ЧИСТО ДЛЯ ТЕСТИРОВАНИЯ
 */
//// Класс для автоматического обновления данных
class DataUpdater(private val repository: DataRepository) {
    init {
        thread {
            while (true) {
                when (Random.Default.nextInt(3)) {
                    0 -> repository.updateData(newUser = "User_${Random.Default.nextInt(1, 100)}")
                    1 -> repository.updateData(newOrder = Random.Default.nextInt(100, 200))
                    2 -> repository.updateData(newPrice = Random.Default.nextDouble(50.0, 150.0))
                }
                val millis = Random.Default.nextLong(10000L)
                Thread.sleep(millis) // Симуляция времени между обновлениями
            }
        }
    }
}