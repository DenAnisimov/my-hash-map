package org.example;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        MyHashMap<String, Integer> myHashMap = new MyHashMap<>();
        myHashMap.put("a", 1);
        myHashMap.put("b", 2);
        myHashMap.put("c", 3);
        myHashMap.put("d", 4);
        myHashMap.put("e", 5);
        myHashMap.put("f", 6);
        myHashMap.put("g", 7);
        myHashMap.put("h", 8);
        myHashMap.put("i", 9);
        myHashMap.put("j", 10);
        myHashMap.put("k", 11);
        myHashMap.put("l", 12);
        myHashMap.put("m", 13);
        myHashMap.put("n", 14);
        myHashMap.put("o", 15);
        myHashMap.put("p", 16);
        System.out.println(myHashMap.get("a"));
    }
}