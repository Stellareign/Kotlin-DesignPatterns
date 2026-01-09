package tasks.task_6_10_ObserverMut

import tasks.task_6_10_ObserverMut.observers.MutableObserver



// Репозиторий данных
object DataRepository {
    var _userData: String = "User_Initial"
    var _orderData: Int = 100
    var _priceData: Double = 99.99

    val userData = MutableObserver(_userData)
    val orderData = MutableObserver(_orderData)
    val priceData = MutableObserver(_priceData)

    val observer = MutableObserver(::updateData)

    fun updateData(newUser: String? = null, newOrder: Int? = null, newPrice: Double? = null) {
        newUser?.let { _userData = it }
        newOrder?.let { _orderData = it }
        newPrice?.let { _priceData = it }

        // Обновляем наблюдателей только если значение действительно изменилось
        newUser?.let {
            if (userData.currentValue != it) {
                userData.currentValue = it
            }
        }
        newOrder?.let {
            if (orderData.currentValue != it) {
                orderData.currentValue = it
            }
        }
        newPrice?.let {
            if (priceData.currentValue != it) {
                priceData.currentValue = it
            }
        }
    }
}