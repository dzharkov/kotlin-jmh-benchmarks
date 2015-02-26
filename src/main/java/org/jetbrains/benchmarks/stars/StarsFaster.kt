package org.jetbrains.benchmarks.stars

import org.openjdk.jmh.annotations.*;

import java.util.Comparator
import java.util.concurrent.TimeUnit
import org.openjdk.jmh.infra.*

/**
 * @author Denis Zharkov
 */
[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open public class StarsKotlinFaster() {
    public open class ComparableComparator<T : Comparable<T>>() : Comparator<T?> {
        public override fun compare(lhs : T?, rhs : T?) : Int {
            return lhs!!.compareTo(rhs!!)
        }
    }

    open class AVLTree<K, D>(var comparator : Comparator<K?>) {

        class NodeGetter {
            fun height<K, D>(node : Node<K, D>?) : Int {
                return if (node != null) node.height else 0
            }

            fun size<K, D>(node : Node<K, D>?) : Int {
                return if (node != null) node.size else 0
            }
        }

        class object {
            val ng : NodeGetter = NodeGetter()
        }


        inner class Node<K,D>(var key : K, var data : D) {

            var size : Int = 1
            var left : Node<K,D>? = null
                set(value) {
                    if (!value.identityEquals($left))
                    {
                        $left = value
                        if ($left != null) {
                            $left!!.parent = this
                        }
                    }
                }
            var right : Node<K,D>? = null
                set(value) {
                    if (!value.identityEquals($right))
                    {
                        $right = value
                        if ($right != null) {
                            $right!!.parent = this
                        }
                    }
                }
            var parent : Node<K,D>? = null
            var height : Int = 1
            var balance : Int = 0
            fun updateHeight() : Unit {
                val leftHeight = ng.height<K,D>(left)
                val rightHeight = ng.height(right)
                height = 1 + Math.max(leftHeight, rightHeight)
            }
            fun updateBalance() : Unit {
                left?.updateHeight()
                right?.updateHeight()
                val leftHeight = ng.height<K,D>(left)
                val rightHeight = ng.height(right)
                balance = leftHeight - rightHeight
            }

            fun onChildrenChange() : Unit {
                updateBalance()
                updateSize()
            }

            fun updateSize() : Unit {
                val leftSize = ng.size(left)
                val rightSize = ng.size(right)
                size = 1 + leftSize +  rightSize
            }
        }
        protected open fun buildNode<K,D>(key : K, data : D) : Node<K,D> {
            return Node<K,D>(key, data)
        }
        var root : Node<K,D>? = null
        fun insert(key : K, data : D) : Unit {
            root = insert(root, key, data)
            root!!.parent = null
            root!!.onChildrenChange()
        }
        fun insert(node : Node<K,D>?, key : K, data : D) : Node<K,D> {
            if (node == null)
            {
                return buildNode<K,D>(key, data)
            }

            if (comparator.compare(node.key, key) > 0)
            {
                node.left = (insert(node.left, key, data))
            }
            else
            {
                node.right = (insert(node.right, key, data))
            }

            node.onChildrenChange()
            if (Math.abs(node.balance) > 1)
            {
                if (node.balance > 1)
                {
                    if (node.left!!.balance < 0)
                    {
                        node.left = (rotateLeft(node.left!!))
                    }

                    return rotateRight(node)
                }
                else
                {
                    if (node.right!!.balance > 0)
                    {
                        node.right = (rotateRight(node.right!!))
                    }

                    return rotateLeft(node)
                }
            }

            return node
        }
        private fun rotateRight(node : Node<K,D>) : Node<K,D> {
            val wasLeft = node.left!!
            node.left = (wasLeft.right)
            node.onChildrenChange()
            wasLeft.right = (node)
            wasLeft.onChildrenChange()
            return wasLeft
        }
        private fun rotateLeft(node : Node<K,D>) : Node<K,D> {
            val wasRight = node.right!!
            node.right = (wasRight.left)
            node.onChildrenChange()
            wasRight.left = (node)
            wasRight.onChildrenChange()
            return wasRight
        }
        fun find(key : K) : Node<K,D>? {
            return find(root, key)
        }

        fun find(node : Node<K,D>?, key : K) : Node<K,D>? {
            if (node == null)
            {
                return null
            }

            if (node.key.equals(key))
            {
                return node
            }

            if (comparator.compare(node.key, key) > 0)
            {
                return find(node.left, key)
            }
            else
            {
                return find(node.right, key)
            }
        }
        {
            this.comparator = comparator
        }

        fun elementOrder(key : K) : Int {
            var node = find(key)
            if (node == null)
            {
                return -1
            }

            var result = 0
            do
            {
                if (node!!.left != null)
                {
                    result += ((node!!.left as Node<K,D>)).size
                }

                while (node!!.parent?.left == node)
                {
                    node = node!!.parent
                }
                if (node!!.parent != null)
                {
                    result++
                }

                node = node!!.parent
            }
            while (node != null)
            return result
        }

    }

    class PointY(var y : Int, var id : Int) : Comparable<PointY> {
        public override fun compareTo(o : PointY) : Int {
            if (y != o.y)
            {
                return y - o.y
            }

            return id - o.id
        }
    }

    [Benchmark]
    public fun solve(bh : Blackhole) : Unit {
        val tree = AVLTree<PointY, Int>(ComparableComparator<PointY>())
        val n = StarsData.getN()
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


}
