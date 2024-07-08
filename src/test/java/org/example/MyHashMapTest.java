package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyHashMapTest {
    @Test
    public void testCreation() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertEquals(0, map.size(), "Initial size should be 0");
    }

    @Test
    public void testPutAndGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertEquals(1, map.get("one"));
        assertEquals(2, map.get("two"));
        assertEquals(3, map.get("three"));
        assertEquals(3, map.size(), "Size should be 3 after adding three elements");
    }

    @Test
    public void testUpdateValue() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        assertEquals(1, map.get("one"));

        map.put("one", 10);
        assertEquals(10, map.get("one"), "Value should be updated to 10");
    }

    @Test
    public void testCollisionHandling() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        map.put("one", 10);
        map.put("two", 20);

        assertEquals(10, map.get("one"), "Value should be updated to 10");
        assertEquals(20, map.get("two"), "Value should be updated to 20");
        assertEquals(3, map.get("three"), "Value should remain 3");
    }

    @Test
    public void testResize() {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(i, "Value" + i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("Value" + i, map.get(i), "Values should match after resize");
        }

        assertTrue(map.size() > 16, "Size should be greater than " +
                "initial capacity after adding more elements");
    }
}
