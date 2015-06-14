package com.artem.myhashmap;

public interface MyMap <K, V> {
    void  put (K key, V value);
    V get (K key);
    int size();
    boolean isEmpty();
    void remove (K key);
    boolean containsKey(K key);
    boolean containsValue(V value);
}
