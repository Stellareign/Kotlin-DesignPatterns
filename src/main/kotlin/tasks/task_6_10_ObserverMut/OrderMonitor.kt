package tasks.task_6_10_ObserverMut

import tasks.task_6_10_ObserverMut.DataRepository
import kotlin.concurrent.thread

/**
 * Мониторинг данных с периодическим опросом
 */


//class OrderMonitor(private val repository: DataRepository) {
//    init {
//        thread {
//            var lastValue = repository.orderData
//            while (true) {
//                println("OrderMonitor: запрос к DataRepository")
//                if (repository.orderData != lastValue) {
//                    println("OrderMonitor: Обнаружено изменение данных заказа: ${repository.orderData}")
//                    lastValue = repository.orderData
//                }
//                Thread.sleep(1000)
//            }
//        }
//    }
//}