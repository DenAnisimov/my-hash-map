package org.example;

public class MyNode<K, V> {
    private final K key;
    private V value;
    private MyNode<K, V> next;

    public MyNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public MyNode<K, V> getNext() {
        return next;
    }

    public void setNext(MyNode<K, V> next) {
        this.next = next;
    }
}
