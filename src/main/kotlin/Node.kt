package org.example

class Node<T : Comparable<T>>(var value: T, var height: Int = 1) {
    var left: Node<T>? = null
    var right: Node<T>? = null

    fun updateHeight() {
        height = 1 + maxOf(left?.height ?: 0, right?.height ?: 0)
    }

    fun balanceFactor(): Int {
        return (left?.height ?: 0) - (right?.height ?: 0)
    }
}