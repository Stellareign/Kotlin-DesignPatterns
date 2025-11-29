package tasks.task_6_10_ObserverMut

import tasks.task_6_10_ObserverMut.auxiliaryClasses.DataRepository
import kotlin.concurrent.thread

class UserMonitor(private val repository: DataRepository) {
    init {
        thread {
            var lastValue = repository.userData
            while (true) {
                println("UserMonitor: запрос к DataRepository")
                if (repository.userData != lastValue) {
                    println("UserMonitor: Обнаружено изменение данных пользователя: ${repository.userData}")
                    lastValue = repository.userData
                }
                Thread.sleep(1000)
            }
        }
    }
}