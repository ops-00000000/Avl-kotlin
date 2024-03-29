package org.example

class AVLTree<T : Comparable<T>> {
    private var root: Node<T>? = null

    fun insert(value: T) {
        root = insert(root, value)
    }

    private fun insert(node: Node<T>?, value: T): Node<T> {
        if (node == null) return Node(value)

        if (value < node.value) {
            node.left = insert(node.left, value)
        } else if (value > node.value) {
            node.right = insert(node.right, value)
        } else {
            return node
        }

        node.updateHeight()
        return balance(node)
    }

    private fun balance(node: Node<T>): Node<T> {
        val balanceFactor = node.balanceFactor()

        if (balanceFactor > 1) {
            if ((node.left?.balanceFactor() ?: 0) < 0) {
                node.left = rotateLeft(node.left)
            }
            return rotateRight(node)
        }

        if (balanceFactor < -1) {
            if ((node.right?.balanceFactor() ?: 0) > 0) {
                node.right = rotateRight(node.right)
            }
            return rotateLeft(node)
        }

        return node
    }

    private fun rotateRight(node: Node<T>?): Node<T> {
        val leftNode = node?.left
        node?.left = leftNode?.right
        leftNode?.right = node

        node?.updateHeight()
        leftNode?.updateHeight()

        return leftNode ?: node!!
    }

    private fun rotateLeft(node: Node<T>?): Node<T> {
        val rightNode = node?.right
        node?.right = rightNode?.left
        rightNode?.left = node

        node?.updateHeight()
        rightNode?.updateHeight()

        return rightNode ?: node!!
    }

    fun find(value: T): Node<T>? {
        var current = root
        while (current != null) {
            current = when {
                value < current.value -> current.left
                value > current.value -> current.right
                else -> return current
            }
        }
        return null
    }

    fun delete(value: T) {
        root = delete(root, value)
    }

    private fun delete(node: Node<T>?, value: T): Node<T>? {
        node ?: return null

        when {
            value < node.value -> node.left = delete(node.left, value)
            value > node.value -> node.right = delete(node.right, value)
            else -> {
                when {
                    node.left == null -> return node.right
                    node.right == null -> return node.left
                    else -> {
                        val tmp = findMinNode(node.right!!)
                        node.value = tmp.value
                        node.right = delete(node.right, tmp.value)
                    }
                }
            }
        }

        node.updateHeight()
        return balance(node)
    }

    private fun findMinNode(node: Node<T>): Node<T> {
        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    fun printTree() {
        println("-----------------------------------------------------")
        root?.let { printTree(it) } ?: println("Дерево пусто")
        println("-----------------------------------------------------")
    }

    private fun printTree(node: Node<T>, level: Int = 0, prefix: String = "") {
        node.right?.let { printTree(it, level + 1, "    $prefix") }

        println("$prefix+- ${node.value}")

        node.left?.let { printTree(it, level + 1, "    $prefix") }
    }


}