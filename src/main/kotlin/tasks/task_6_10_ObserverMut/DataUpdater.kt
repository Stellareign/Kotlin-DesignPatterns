package tasks.task_6_10_ObserverMut

import tasks.task_6_10_ObserverMut.auxiliaryClasses.DataRepository
import kotlin.concurrent.thread
import kotlin.random.Random

// Класс для автоматического обновления данных
class DataUpdater(private val repository: DataRepository) {
    init {
        thread {
            while (true) {
                when (Random.nextInt(3)) {
                    0 -> repository.updateData(newUser = "User_${Random.nextInt(1, 100)}")
                    1 -> repository.updateData(newOrder = Random.nextInt(100, 200))
                    2 -> repository.updateData(newPrice = Random.nextDouble(50.0, 150.0))
                }
                val millis = Random.nextLong(10000L)
                Thread.sleep(millis) // Симуляция времени между обновлениями
            }
        }
    }
}