package org.jetbrains.benchmarks.stars;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Comparator;
import java.util.concurrent.TimeUnit;

/**
 * @author Denis Zharkov
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StarsJava {
    public class ComparableComparator<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T lhs, T rhs) {
            return lhs.compareTo(rhs);
        }
    }

    @Param({"100", "1000", "100000"})
    int size;

    @Setup
    public void init() {
        StarsData.init();
    }

    class AVLTree<K,D> {
        class Node<K,D> {
            K key;
            D data;
            Node<K,D> left = null;
            Node<K,D> right = null;
            Node<K,D> parent = null;
            int size = 1;
            int height = 1;
            int balance = 0;

            Node(K key, D data) {
                this.key = key;
                this.data = data;
            }

            void updateHeight() {
                height = 1;
                if (left != null && right != null) {
                    height = 1 + Math.max(left.height, right.height);
                } else {
                    Node<K,D> child = left != null ? left : right;
                    if (child != null) {
                        height = child.height + 1;
                    }
                }
            }

            void updateBalance() {
                if (left != null) {
                    left.updateHeight();
                }

                if (right != null) {
                    right.updateHeight();
                }

                int leftHeight = left == null ? 0 : left.height;
                int rightHeight = right == null ? 0 : right.height;

                balance = leftHeight - rightHeight;
            }

            void onChildrenChange() {
                updateBalance();
            }

            void setLeft(Node<K,D> left) {
                if (left != this.left) {
                    this.left = left;

                    if (left != null) {
                        left.parent = this;
                    }
                }
            }

            void setRight(Node<K,D> right) {
                if (right != this.right) {
                    this.right = right;
                    if (right != null) {
                        right.parent = this;
                    }
                }
            }
        }

        protected Node<K,D> buildNode(K key, D data) {
            return new Node<K,D>(key, data);
        }

        Node<K,D> root = null;
        Comparator<K> comparator;

        AVLTree(Comparator<K> comparator) {
            this.comparator = comparator;
        }

        void insert(K key, D data) {
            root = insert(root, key, data);
            root.parent = null;
            root.onChildrenChange();
        }

        Node<K,D> insert(Node<K,D> node, K key, D data) {
            if (node == null) {
                return buildNode(key, data);
            }

            if (comparator.compare(node.key, key) > 0) {
                node.setLeft(insert(node.left, key, data));
            } else {
                node.setRight(insert(node.right, key, data));
            }

            node.onChildrenChange();

            if (Math.abs(node.balance) > 1) {
                if (node.balance > 1) {
                    if (node.left.balance < 0) {
                        node.setLeft(rotateLeft(node.left));
                    }

                    node = rotateRight(node);
                } else {
                    if (node.right.balance > 0) {
                        node.setRight(rotateRight(node.right));
                    }

                    node = rotateLeft(node);
                }
            }

            return node;
        }

        private Node<K,D> rotateRight(Node<K,D> node) {
            Node<K,D> wasLeft = node.left;

            node.setLeft(wasLeft.right);
            node.onChildrenChange();

            wasLeft.setRight(node);
            wasLeft.onChildrenChange();

            return wasLeft;
        }

        private Node<K,D> rotateLeft(Node<K,D> node) {
            Node<K,D> wasRight = node.right;

            node.setRight(wasRight.left);
            node.onChildrenChange();

            wasRight.setLeft(node);
            wasRight.onChildrenChange();

            return wasRight;
        }

        Node<K,D> find(K key) {
            return find(root, key);
        }

        Node<K,D> find(Node<K,D> node, K key) {
            if (node == null) {
                return null;
            }

            if (node.key.equals(key)) {
                return node;
            }

            if (comparator.compare(node.key, key) > 0) {
                return find(node.left, key);
            } else {
                return find(node.right, key);
            }
        }
    }

    class OrderAVLTree<K,D> extends AVLTree<K,D> {

        class SizedNode<K,D> extends Node<K,D> {
            SizedNode(K key, D data) {
                super(key, data);
            }

            @Override
            void onChildrenChange() {
                super.onChildrenChange();
                updateSize();
            }

            void updateSize() {
                int leftSize = left == null ? 0 : left.size;
                int rightSize = right == null ? 0 : right.size;
                size = 1 + leftSize + rightSize;
            }
        }

        @Override
        protected Node<K,D> buildNode(K key, D data) {
            return new SizedNode<K,D>(key, data);    //To change body of overridden methods use File | Settings | File Templates.
        }

        OrderAVLTree(Comparator<K> comparator) {
            super(comparator);
        }

        int elementOrder(K key) {
            Node<K,D> node = find(key);
            if (node == null) {
                return -1;
            }

            int result = 0;

            do {
                if (node.left != null) {
                    result += ((SizedNode<K,D>)node.left).size;
                }

                while (node.parent != null && node.parent.left == node) {
                    node = node.parent;
                }

                if (node.parent != null) {
                    result++;
                }

                node = node.parent;
            } while (node != null);

            return result;
        }
    }

    class PointY implements Comparable<PointY> {
        int y;
        int id;

        PointY(int y, int id) {
            this.y = y;
            this.id = id;
        }

        @Override
        public int compareTo(PointY o) {
            if (y != o.y) {
                return y - o.y;
            }

            return id - o.id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) { return true; }
            if (o == null || getClass() != o.getClass()) { return false; }

            PointY pointY = (PointY) o;

            if (id != pointY.id) { return false; }
            if (y != pointY.y) { return false; }

            return true;
        }
    }

    @Benchmark
    public void solve(Blackhole bh) {
        OrderAVLTree<PointY,Integer> tree = new OrderAVLTree<PointY, Integer>(new ComparableComparator<PointY>());

        int n = size;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int y = StarsData.getX();

            PointY p = new PointY(y, i);

            tree.insert(p, i);

            result[tree.elementOrder(p)]++;
        }

        for (int i = 0; i < n; i++) {
            bh.consume(result[i]);
        }
    }
}
