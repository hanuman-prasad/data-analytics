package edu.elearning.service.datacache.cache;

public interface CacheFactory<T> {

    Cache createCache(T entity, long maxCacheSize);
}
