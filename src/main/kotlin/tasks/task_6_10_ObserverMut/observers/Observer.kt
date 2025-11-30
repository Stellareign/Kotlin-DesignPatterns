package tasks.task_6_10_ObserverMut.observers


fun interface Observer <T> {

   fun onChanged(newValue: T)
}