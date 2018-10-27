package utils

open class Stack<T> {
    val elements: MutableList<T> = mutableListOf()
    fun isEmpty() = elements.isEmpty()
    val size get() = elements.size
    fun push(item: T) = elements.add(item)
    fun pop(): T {
        val item = elements.last()
        if (!isEmpty()) {
            elements.removeAt(elements.size - 1)
        }
        return item
    }

    fun peek(): T = elements.last()

    override fun toString(): String = elements.toString()
}