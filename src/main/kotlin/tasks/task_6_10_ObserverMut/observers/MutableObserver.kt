package tasks.task_6_10_ObserverMut.observers

import kotlin.collections.mutableSetOf

class MutableObserver<T>(initialValue: T) : Observable<T> {

    override var currentValue: T = initialValue
        set(value) {
            field = value
            notifyObservers()
        }

    private var _observers = mutableSetOf<Observer<T>>()

    override val observers
        get() = _observers.toList()

    override fun registerObserver(observer: Observer<T>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun unregisterObserver(observer: Observer<T>) {
        _observers.remove(observer)
    }

    override fun notifyObservers() {
        super.notifyObservers()
    }
}