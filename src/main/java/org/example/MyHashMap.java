package org.example;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

/**
 * Простая реализация структуры данных хеш-таблица.
 *
 * @param <K> ключ.
 * @param <V> значение.
 */
public class MyHashMap<K, V> implements Iterable<MyNode<K, V>> {
    private final static Logger LOGGER = Logger.getLogger(MyHashMap.class.getName());

    private final int CAPACITY = 16;
    private final float LOAD_FACTOR = 0.75f;

    private MyNode<K, V>[] table;
    private int size;
    private int threshold;

    /**
     * Создает пустую MyHashMap с начальной емкостью по умолчанию (16) и коэффициентом загрузки (0,75).
     */
    public MyHashMap() {
        LOGGER.info("HashMap was created");

        this.table = new MyNode[CAPACITY];
        this.threshold = (int) (CAPACITY * LOAD_FACTOR);
    }

    /**
     * Вычисляет хеш-код для указанного ключа.
     *
     * @param key ключ
     * @return хеш-код для указанного ключа
     */
    private int hash(Object key) {
        LOGGER.info("Method hash worked");

        int h;
        if (key == null) {
            return 0;
        } else {
            h = key.hashCode();
            h = h ^ (h >>> 16);

            return h;
        }
    }

    /**
     * Вычисляет бакет для объекта
     *
     * @param h хеш-код объекта
     * @return индекс для объекта
     */
    private int index(int h) {
        LOGGER.info("Bucket was calculated for the Object: " + h);
        return h & table.length - 1;
    }

    /**
     * Связывает указанное значение с указанным ключом в этой карте.
     *
     * @param key   ключ, с которым должно быть связано указанное значение
     * @param value значение, которое должно быть связано с указанным ключом
     */
    public void put(K key, V value) {
        LOGGER.info("Method put worked");

        int h = index(hash(key));
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

    /**
     * Возвращает значение, с которым связано указанное ключ, или {@code null}, если эта карта не содержит сопоставления для ключа.
     *
     * @param key ключ, значение которого необходимо вернуть
     * @return значение, с которым связано указанное ключ, или {@code null}, если эта карта не содержит сопоставления для ключа
     */
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

    /**
     * Изменяет размер хеш-таблицы, когда количество элементов превышает пороговое значение.
     */
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

    /**
     * Возвращает количество сопоставлений ключ-значение в этой карте.
     *
     * @return количество сопоставлений ключ-значение в этой карте
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает итератор по элементам типа {@code MyNode<K, V>}.
     *
     * @return итератор
     */
    @Override
    public Iterator<MyNode<K, V>> iterator() {
        return new MyHashMapIterator();
    }

    /**
     * Итератор по хеш-таблице.
     */
    private class MyHashMapIterator implements Iterator<MyNode<K, V>> {
        private int bucketIndex = 0;
        private MyNode<K, V> currentNode = null;

        public MyHashMapIterator() {
            advanceToNextNonEmptyBucket();
        }

        /**
         * Переходит к следующему непустому бакету.
         */
        private void advanceToNextNonEmptyBucket() {
            while (bucketIndex < table.length && (currentNode == null)) {
                currentNode = table[bucketIndex++];
            }
        }

        /**
         * Возвращает {@code true}, если итерация имеет больше элементов.
         *
         * @return {@code true}, если итерация имеет больше элементов
         */
        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        /**
         * Возвращает следующий элемент в итерации.
         *
         * @return следующий элемент в итерации
         * @throws NoSuchElementException если в итерации больше нет элементов
         */
        @Override
        public MyNode<K, V> next() {
            if (currentNode == null) {
                throw new NoSuchElementException();
            }
            MyNode<K, V> node = currentNode;
            currentNode = currentNode.getNext();
            if (currentNode == null) {
                advanceToNextNonEmptyBucket();
            }
            return node;
        }
    }
}
