package tasks.task_6_10_ObserverMut

import tasks.task_6_10_ObserverMut.observers.MutableObserver
import kotlin.math.round

// Репозиторий данных
object DataRepository {
//        var userData: String = "User_Initial"
//    var orderData: Int = 100
//    var priceData: Double = 99.99
    val _userData = mutableListOf<String>()
    val _orderData = mutableListOf<Int>()
    val _priceData = mutableListOf<Double>()

    val userData = MutableObserver(_userData.toList())
    val orderData = MutableObserver(_orderData.toList())
    val priceData = MutableObserver(_priceData.toList())

    // Метод обновления данных
//    fun updateData(newUser: String? = null, newOrder: Int? = null, newPrice: Double? = null) {
//        newUser?.let { userData = it }
//        newOrder?.let { orderData = it }
//        newPrice?.let { priceData = round(it * 100) / 100 }
//    }
    fun updateData() {
        userData.currentValue = _userData.toList()
        orderData.currentValue = _orderData.toList()
        priceData.currentValue = _priceData.toList()
    }
}