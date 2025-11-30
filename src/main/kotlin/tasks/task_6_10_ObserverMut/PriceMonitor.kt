package tasks.task_6_10_ObserverMut

import tasks.task_6_10_ObserverMut.DataRepository
import kotlin.concurrent.thread

class PriceMonitor(private val repository: DataRepository) {
    init {
        thread {
            var lastValue = repository.priceData
            while (true) {
                println("PriceMonitor: запрос к DataRepository")
                if (repository.priceData != lastValue) {
                    println("PriceMonitor: Обнаружено изменение цены: ${repository.priceData}")
                    lastValue = repository.priceData
                }
                Thread.sleep(1000)
            }
        }
    }
}