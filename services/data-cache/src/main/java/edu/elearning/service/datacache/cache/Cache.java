package edu.elearning.service.datacache.cache;

public interface Cache<K, V> {
    int size();

    V get(K key);

    void put(K key, V value);
}
