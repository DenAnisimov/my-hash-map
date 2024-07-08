package org.example;

import java.util.logging.Logger;

public class MyHashMap<K, V> {
    private final static Logger LOGGER = Logger.getLogger(MyHashMap.class.getName());

    private final int CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;

    private MyNode<K, V>[] table;
    private int size;
    private int threshold;

    public MyHashMap() {
        LOGGER.info("HashMap was created");

        this.table = new MyNode[CAPACITY];
        this.threshold = (int) (CAPACITY * LOAD_FACTOR);
    }

    private int hash(Object key) {
        LOGGER.info("Method hash worked");

        int h;
        if (key == null) {
            return 0;
        } else {
            h = key.hashCode();
            h = h ^ (h >>> 16);

            h = h & table.length - 1;

            LOGGER.info("Bucket was calculated for the Object: " + h);

            return h;
        }
    }

    public void put(K key, V value) {
        LOGGER.info("Method put worked");

        int h = hash(key);
        MyNode<K, V> node = new MyNode<>(key, value);

        if (table[h] == null) {
            table[h] = node;
            LOGGER.info("Object was added to the Bucket");
        } else {
            MyNode<K, V> current = table[h];

            while (current != null) {
                if (current.getKey().equals(key)) {
                    LOGGER.info("Value was changed from " + current.getValue() + " to " + value);
                    current.setValue(value);
                    return;
                }
                if (current.getNext() == null) {
                    LOGGER.warning("Collision: Object already exists in the Bucket");
                    LOGGER.info("Adding new Object to the Bucket");
                    current.setNext(node);
                    break;
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
        LOGGER.info("Method get worked");
        int h = hash(key);
        MyNode<K, V> node = table[h];
        while (node != null) {
            if (key.equals(node.getKey())) {
                LOGGER.info("Value was found: " + node.getValue());
                return node.getValue();
            }
            node = node.getNext();
        }
        return null;
    }

    private void resize() {
        LOGGER.info("Method resize worked");
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
