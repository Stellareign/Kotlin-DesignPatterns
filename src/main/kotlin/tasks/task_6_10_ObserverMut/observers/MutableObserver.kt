package tasks.task_6_10_ObserverMut.observers

class MutableObserver<T>(initialValue: T) : Observable<T> {

    override var currentValue: T = initialValue
        set(value) {
            field = value
            notifyObservers()
        }
    private var _observers: MutableList<Observer<T>> = mutableListOf()

    override val observers
        get() = _observers.toList()


    override fun registerObserver(observer: Observer<T>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun unregisterObserver(observer: Observer<T>) {
       _observers.remove(observer)
        observer.onChanged(currentValue)
    }

    override fun notifyObservers() {
        super.notifyObservers()
    }
}