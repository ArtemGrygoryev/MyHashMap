package com.artem.myhashmap;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private int size;
    private Node<K, V>[] storage;
    private int storageSize;
    private float loadFactor;

    public MyHashMap() {
        size = 0;
        storageSize = 16;
        storage = new Node[storageSize];
        loadFactor = 0.75F;
    }

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void put(K key, V value) {
        int storageIndex = getStorageIndex(key);
        Node node = getNode(storageIndex, key);
        if (node != null) {
            node.value = value;
            return;
        }
        storage[storageIndex] = new Node<K, V>(key, value, storage[storageIndex]);
        size++;
        resize();
    }

    private int getStorageIndex(K key) {
        if (key != null)
            return key.hashCode() & (storageSize - 1);
        return 0;
    }

    private Node<K, V> getNode(int storageIndex, K key) {
        Node<K, V> node = storage[storageIndex];
        while (node != null) {
            if (areEqual(node.key, key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    private boolean areEqual(Object first, Object second) {
        return (first == null && second == null || first != null && first.equals(second));
    }

    private void resize() {
        if (size < storageSize * loadFactor)
            return;
        storageSize *= 2;
        Node<K, V>[] newStorage = new Node[storageSize];
        for (int i = 0; i < storageSize / 2; i++) {
            Node<K, V> node = storage[i];
            while (node != null) {
                Node<K, V> nextNode = node.next;
                int storageIndex = getStorageIndex(node.key);
                node.next = newStorage[storageIndex];
                newStorage[storageIndex] = node;
                node = nextNode;
            }
        }
        storage = newStorage;
    }

    @Override
    public V get(K key) {
        int storageIndex = getStorageIndex(key);
        Node<K, V> node = getNode(storageIndex, key);
        if (node != null)
            return node.value;
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(K key) {
        int storageIndex = getStorageIndex(key);
        Node<K, V> node = storage[storageIndex];
        if (node == null)
            return;
        if (areEqual(node.key, key)) {
            storage[storageIndex] = node.next;
            size--;
            return;
        }
        while (node.next != null) {
            if (areEqual(node.next.key, key)) {
                node.next = node.next.next;
                size--;
                return;
            }
            node = node.next;
        }
    }

    @Override
    public boolean containsKey(K key) {
        int storageIndex = getStorageIndex(key);
        Node node = getNode(storageIndex, key);
        return node != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < storageSize; i++) {
            Node<K, V> node = storage[i];
            while (node != null) {
                if (areEqual(node.value, value))
                    return true;
                node = node.next;
            }
        }
        return false;
    }
}
