package edu.elearning.service.datacache.cache;

public interface CachePersister<K, V> {

    void persist(K key, V value);

    int populate(Cache<K, V> targetCache);

    V getValue(K key);
}
