// Jonathan Harrington - Assignment 3 - 06,02,2022

import java.util.HashSet;
    // Task create redblack tree. Might as well use the comparable class built in
public class RedBlackTree<K extends Comparable<K>, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    // Task define its main contraints
    class Node {
        K key;
        HashSet<V> values;
        Node left, right;
        boolean color;
// define a basic node
        Node(K key, V value, boolean color) {
            this.key = key;
            this.values = new HashSet<>();
            this.values.add(value);
            this.color = color;
        }
    }
    // define the root
    private Node root;

    // task - make a few basic methods to check the value of our node
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    // simple get method
    public HashSet<V> get(K key) {
        return get(root, key);
    }
    // We need a hash set to combine all of the same values into a single node
    private HashSet<V> get(Node x, K key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.values;
        }
        return new HashSet<>();
    }
    // puts the value simple version
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }
    // This helper function allows us to put the pair into the spesfic node.
    private Node put(Node h, K key, V value) {
        if (h == null) return new Node(key, value, RED);

        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left  = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else              h.values.add(value);

        // balance the tree
        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);

        return h;
    }
    // Right rotation
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    // Left rotation
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    // helper function to Flips the color
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
}
