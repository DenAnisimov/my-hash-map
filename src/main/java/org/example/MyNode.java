package org.example;

/**
 * Узел хэш-таблицы, используемый для хранения пар ключ-значение.
 *
 * @param <K> тип ключей, поддерживаемых этим узлом
 * @param <V> тип значений, сопоставленных с ключами
 */
public class MyNode<K, V> {
    private final K key;
    private V value;
    private MyNode<K, V> next;

    /**
     * Создает новый узел с указанными ключом и значением.
     *
     * @param key   ключ
     * @param value значение
     */
    public MyNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Возвращает ключ.
     *
     * @return ключ
     */
    public K getKey() {
        return key;
    }

    /**
     * Возвращает значение.
     *
     * @return значение
     */
    public V getValue() {
        return value;
    }

    /**
     * Устанавливает новое значение.
     *
     * @param value новое значение
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Возвращает следующий узел.
     *
     * @return следующий узел
     */
    public MyNode<K, V> getNext() {
        return next;
    }

    /**
     * Устанавливает следующий узел.
     *
     * @param next новый следующий узел
     */
    public void setNext(MyNode<K, V> next) {
        this.next = next;
    }
}
