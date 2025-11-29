import tasks.task_6_10_ObserverMut.auxiliaryClasses.DataRepository
import tasks.task_6_10_ObserverMut.DataUpdater

fun main() {
    // Запуск обновления данных
    DataUpdater(DataRepository)

    // Подписка на обновления данных пользователя
    DataRepository.userData.registerObserver { newValue ->
        println("UserMonitor: Обнаружено изменение данных пользователя: $newValue")
    }

    // Подписка на обновления данных заказов
    DataRepository.orderData.registerObserver { newValue ->
        println("OrderMonitor: Обнаружено изменение данных заказа: $newValue")
    }

    // Подписка на обновления данных цены
    DataRepository.priceData.registerObserver { newValue ->
        println("PriceMonitor: Обнаружено изменение цены: $newValue")
    }
}


