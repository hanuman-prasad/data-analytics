package edu.elearning.service.datacache.cache;

import edu.elearning.se.AsteriModel;

public interface CacheFactory<T> {

    Cache createCache(AsteriModel entity, long maxCacheSize);
}
