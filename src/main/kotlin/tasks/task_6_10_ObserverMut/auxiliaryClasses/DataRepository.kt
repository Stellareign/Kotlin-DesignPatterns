package tasks.task_6_10_ObserverMut.auxiliaryClasses

import kotlin.math.round

// Репозиторий данных
object DataRepository {
    var userData: String = "User_Initial"
    var orderData: Int = 100
    var priceData: Double = 99.99

    // Метод обновления данных
    fun updateData(newUser: String? = null, newOrder: Int? = null, newPrice: Double? = null) {
        newUser?.let { userData = it }
        newOrder?.let { orderData = it }
        newPrice?.let { priceData = round(it * 100) / 100 }
    }
}