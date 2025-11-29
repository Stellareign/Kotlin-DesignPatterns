package tasks.task_6_10_ObserverMut.observers

import Observer.Observer

fun interface Observer <T> {

   fun onChanged(newValue: T)
}