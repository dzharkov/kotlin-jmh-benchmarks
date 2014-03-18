package org.jetbrains.benchmarks

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.logic.BlackHole

import java.util.Comparator
import java.util.concurrent.TimeUnit
/**
 * @author Denis Zharkov
*/
[State(Scope.Thread)]
[BenchmarkMode(Mode.AverageTime) ]
[OutputTimeUnit(TimeUnit.NANOSECONDS)]
open public class StarsKotlin() {
    public open class ComparableComparator<T : Comparable<T>>() : Comparator<T?> {
        public override fun compare(lhs : T?, rhs : T?) : Int {
            return lhs!!.compareTo(rhs!!)
        }
    }
    open class AVLTree<K, D>(var comparator : Comparator<K?>) {
        open public class Node<K,D>(var key : K, var data : D) {
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
                val curLeft = left
                val curRight = right
                val leftHeight = curLeft?.height ?: 0
                val rightHeight = curRight?.height ?: 0
                height = 1 + Math.max(leftHeight, rightHeight)
            }
            fun updateBalance() : Unit {
                left?.updateHeight()
                right?.updateHeight()

                val curLeft = left
                val curRight = right
                val leftHeight = curLeft?.height ?: 0
                val rightHeight = curRight?.height ?: 0
                balance = leftHeight - rightHeight
            }
            open fun onChildrenChange() : Unit {
                updateBalance()
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

    }

    class OrderAVLTree<K, D>(comparator : Comparator<K?>) : AVLTree<K, D>(comparator) {
        class SizedNode<K,D>(key : K, data : D) : AVLTree<K, D>.Node<K,D>(key, data) {

            override fun onChildrenChange() : Unit {
                super.onChildrenChange()
                updateSize()
            }

            fun updateSize() : Unit {
                val curLeft = left
                val curRight = right
                val leftSize = curLeft?.size ?: 0
                val rightSize = curRight?.size ?: 0
                size = 1 + leftSize + rightSize
            }
        }
        protected override fun buildNode<K,D>(key : K, data : D) : AVLTree<K, D>.Node<K,D> {
            return SizedNode<K,D>(key, data)
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
                    result += ((node!!.left as SizedNode<K,D>)).size
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

    [GenerateMicroBenchmark]
    public fun solve(bh : BlackHole) : Unit {
        val tree = OrderAVLTree<PointY, Int>(ComparableComparator<PointY>())
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
