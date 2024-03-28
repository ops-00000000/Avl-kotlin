package org.example

fun main() {
    val avlTree = AVLTree<Int>()
    avlTree.insert(10)
    avlTree.insert(15)
    avlTree.insert(5)
    avlTree.insert(40)
    avlTree.insert(99)
    avlTree.insert(11)
    avlTree.insert(12)
    avlTree.insert(55)

    println("-----------------------------------------------------")
    avlTree.printTree()
    println("-----------------------------------------------------")
    avlTree.delete(40)
    println("-----------------------------------------------------")
    avlTree.printTree()
    println("-----------------------------------------------------")
    avlTree.delete(10)
    println("-----------------------------------------------------")
    avlTree.printTree()
    println("-----------------------------------------------------")

}