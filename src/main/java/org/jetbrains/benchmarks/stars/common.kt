package org.jetbrains.benchmarks.stars

import org.openjdk.jmh.infra.Blackhole
import java.util.*

class PointY(var y: Int, var id: Int) : Comparable<PointY> {
    public override fun compareTo(other: PointY): Int {
        if (y != other.y) {
            return y - other.y
        }

        return id - other.id
    }
}

fun solve(bh: Blackhole, ng: NodeInfo, n: Int) {
    val tree = KotlinAVLTree<PointY, Int>(compareBy { it }, ng)
    val result = IntArray(n)
    for (i in 0..n - 1) {
        val y = StarsData.getX()
        val p = PointY(y, i)
        tree.insert(p, i)
        result[tree.elementOrder(p)]++
    }

    for (i in 0..n - 1) {
        bh.consume(result[i])
    }
}

abstract class NodeInfo {
    abstract fun height(node: KotlinAVLTree.Node<*, *>?): Int
    abstract fun size(node: KotlinAVLTree.Node<*, *>?): Int
}

public class KotlinAVLTree<K, D>(val comparator: Comparator<K>, val nodeInfo: NodeInfo) {
    inner class Node<K, D>(val key: K, val data: D) {
        var size: Int = 1

        var left: Node<K, D>? = null
            set(value) {
                if (!value.identityEquals($left)) {
                    $left = value
                    if ($left != null) {
                        $left!!.parent = this
                    }
                }
            }

        var right: Node<K, D>? = null
            set(value) {
                if (!value.identityEquals($right)) {
                    $right = value
                    if ($right != null) {
                        $right!!.parent = this
                    }
                }
            }

        var parent: Node<K, D>? = null
        var height: Int = 1
        var balance: Int = 0

        fun updateHeight(): Unit {
            height = 1 + Math.max(nodeInfo.height(left), nodeInfo.height(right))
        }

        fun updateBalance(): Unit {
            left?.updateHeight()
            right?.updateHeight()
            balance = nodeInfo.height(left) - nodeInfo.height(right)
        }

        fun onChildrenChange(): Unit {
            updateBalance()
            updateSize()
        }

        fun updateSize(): Unit {
            size = 1 + nodeInfo.size(left) + nodeInfo.size(right)
        }
    }

    var root: Node<K, D>? = null

    fun insert(key: K, data: D): Unit {
        root = insert(root, key, data)
        val root = root!!
        root.parent = null
        root.onChildrenChange()
    }

    fun insert(node: Node<K, D>?, key: K, data: D): Node<K, D> {
        if (node == null) {
            return Node(key, data)
        }

        if (comparator.compare(node.key, key) > 0) {
            node.left = insert(node.left, key, data)
        } else {
            node.right = insert(node.right, key, data)
        }

        node.onChildrenChange()
        if (Math.abs(node.balance) > 1) {
            if (node.balance > 1) {
                if (node.left!!.balance < 0) {
                    node.left = rotateLeft(node.left!!)
                }

                return rotateRight(node)
            } else {
                if (node.right!!.balance > 0) {
                    node.right = rotateRight(node.right!!)
                }

                return rotateLeft(node)
            }
        }

        return node
    }

    private fun rotateRight(node: Node<K, D>): Node<K, D> {
        val wasLeft = node.left!!
        node.left = wasLeft.right
        node.onChildrenChange()
        wasLeft.right = node
        wasLeft.onChildrenChange()
        return wasLeft
    }

    private fun rotateLeft(node: Node<K, D>): Node<K, D> {
        val wasRight = node.right!!
        node.right = wasRight.left
        node.onChildrenChange()
        wasRight.left = node
        wasRight.onChildrenChange()
        return wasRight
    }

    fun find(key: K, node: Node<K, D>? = root): Node<K, D>? {
        if (node == null) {
            return null
        }

        if (node.key == key) {
            return node
        }

        if (comparator.compare(node.key, key) > 0) {
            return find(key, node.left)
        } else {
            return find(key, node.right)
        }
    }

    fun elementOrder(key: K): Int {
        var node = find(key)
        if (node == null) {
            return -1
        }

        var result = 0
        do {
            node!! // Could be smart cast

            result += node.left?.size ?: 0

            // Could be smart cast
            while (node!!.parent?.left == node) {
                node = node.parent!!
            }

            if (node.parent != null) {
                result++
            }

            node = node.parent
        } while (node != null)

        return result
    }
}
