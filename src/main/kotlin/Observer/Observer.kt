package Observers

fun interface Observer <T> {
    fun onChanged(newValue: T)
}