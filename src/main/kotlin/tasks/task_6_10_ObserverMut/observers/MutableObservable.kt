package tasks.task_6_10_ObserverMut.observers

class MutableObservable <T>(initialValue: T) : Observable<T> {
    currentValue
    _observers
    override observers

    override registerObserver()
    override unregisterObserver()
}