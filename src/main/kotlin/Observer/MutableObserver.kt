import Observer.Observable
import Observers.Observer


class MutableObserver<T> (initialValue : T): Observable<T> {
    private val _observers = mutableSetOf<Observer<T>>()

    override val observers: List<Observer<T>>
        get() = _observers.toList()

    override var currentValue: T = initialValue // публичное поле
        set(value) {
            field = value
            notifyObservers()
        }

    override fun registerObserver(observer: Observer<T>) {
        _observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun notifyObservers() {
        super.notifyObservers()
    }

    override fun unregisterObserver(observer: Observer<T>) {
       _observers.remove(observer)
    }
}