package edu.elearning.service.datacache.cache;

import com.google.common.cache.CacheBuilder;

public class DefaultCache<K, V> implements Cache<K, V> {

    private final String name;
    private final CachePersister<K, V> persister;
    private final com.google.common.cache.Cache<K, V> cache;

    public DefaultCache(String name, CachePersister persister, com.google.common.cache.Cache<K, V> cache) {
        this.name = name;
        this.persister = persister;
        this.cache = CacheBuilder.newBuilder().build();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void put(K key, V value) {
        // no ops
    }
}
