package org.example;

public class MyHashMap<K, V> {
    private final int CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;

    private MyNode<K, V>[] table;
    private int size;
    private int threshold;

    public MyHashMap() {
        this.table = new MyNode[CAPACITY];
        this.threshold = (int) (CAPACITY * LOAD_FACTOR);
    }

    private int hash(Object key) {
        int h;
        if (key == null) {
            return 0;
        } else {
            h = key.hashCode();
            h = h ^ (h >>> 16);

            h = h & table.length - 1;

            return h;
        }
    }

    public void put(K key, V value) {
        int h = hash(key);
        MyNode<K, V> node = new MyNode<>(key, value);

        if (table[h] == null) {
            table[h] = node;
        } else {
            MyNode<K, V> current = table[h];

            while (current != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                }
                if (current.getNext() == null) {
                    current.setNext(node);
                }
                current = current.getNext();
            }
        }
        size++;
        if (size > threshold) {
            resize();
        }
    }

    public V get(K key) {
        int h = hash(key);
        MyNode<K, V> node = table[h];
        while (node != null) {
            if (key.equals(node.getKey())) {
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }

    public void resize() {
        int newCapacity = table.length * 2;
        threshold = (int) (newCapacity * LOAD_FACTOR);
        MyNode<K, V>[] newTable = new MyNode[newCapacity];

        for (MyNode<K, V> node : table) {
            while (node != null) {
                MyNode<K, V> next = node.getNext();
                int hash = hash(node.getKey());

                node.setNext(newTable[hash]);
                newTable[hash] = node;

                node = next;
            }
        }

        table = newTable;
    }

    public int size() {
        return size;
    }
}
