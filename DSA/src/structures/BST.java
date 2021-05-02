package structures;

import java.util.TreeMap;

public class BST {

    private TreeMap<Integer, Integer> bst;
    private int size;

    public BST() {
        this.bst = new TreeMap<>();
        this.size = 0;
    }

    void add(int num) {
        int count = bst.getOrDefault(num, 0) + 1;
        bst.put(num, count);
        size++;
    }

    void remove(int num) {
        int count = bst.getOrDefault(num, 1) - 1;
        if (count > 0) bst.put(num, count);
        else bst.remove(num);
        size--;
    }

    int removeMin() {
        int key = bst.firstKey();
        remove(key);
        return key;
    }

    int removeMax() {
        int key = bst.lastKey();
        remove(key);
        return key;
    }

    boolean containsKey(int key) {
        return bst.containsKey(key);
    }

    int firstKey() {
        return bst.firstKey();
    }

    int lastKey() {
        return bst.lastKey();
    }
}