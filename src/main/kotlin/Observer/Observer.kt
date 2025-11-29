package Observer

fun interface Observer <T> {
    fun onChanged(newValue: T)
}