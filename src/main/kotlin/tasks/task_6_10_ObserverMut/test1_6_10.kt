package tasks.task_6_10_ObserverMut

import tasks.task_6_10_ObserverMut.auxiliaryClasses.DataRepository

fun main() {
    // Запуск обновления данных
    DataUpdater(DataRepository)

    // Запуск мониторинга
    UserMonitor(DataRepository)
    OrderMonitor(DataRepository)
    PriceMonitor(DataRepository)
}