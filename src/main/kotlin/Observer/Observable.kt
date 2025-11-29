package Observer

/**
 * Интерфейс наблюдаемого объекта
 */
interface Observable <T> {
   val observers:  List<Observer<T>>
   val currentValue:T

    fun registerObserver(observer: Observer<T>)

    fun notifyObservers(){
        for (observer in observers) {
            observer.onChanged(currentValue)
        }
    }
    fun  unregisterObserver(observer: Observer<T>)

}