package Observer

interface Observer <T> {
    fun onChanged(newValue: T)
}