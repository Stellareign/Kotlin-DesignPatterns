package tasks.task_6_10_ObserverMut.observers



interface Observable<T> {
    val currentValue: T
    val observers: List<tasks.task_6_10_ObserverMut.observers.Observer<T>>

    fun registerObserver(observer: Observer<T>)

    fun unregisterObserver(observer: Observer<T>)

    fun notifyObservers() {
        for (observer in observers) {
            observer.onChanged(currentValue)
        }
    }
}