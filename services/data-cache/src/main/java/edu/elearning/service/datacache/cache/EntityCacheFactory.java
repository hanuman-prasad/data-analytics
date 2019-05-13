package edu.elearning.service.datacache.cache;

import com.google.common.cache.CacheBuilder;
import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;
import edu.elearning.service.datacache.conf.CacheConfiguration;

public class EntityCacheFactory implements CacheFactory {

    private final CacheConfiguration cacheConfig;

    public EntityCacheFactory(CacheConfiguration cacheConfig) {
        this.cacheConfig = cacheConfig;
    }


    @Override
    public Cache createCache(Class<? extends AsteriModel> entityClass, UserWebsite website) {

        String cacheName = entityClass.getSimpleName();
        CachePersister persister = new CassandraPersister(website, entityClass);
        com.google.common.cache.Cache cache = initCache();

        DefaultCache defaultCache = new DefaultCache<>(cacheName, persister, cache);

        return defaultCache;
    }

    private com.google.common.cache.Cache initCache() {
        com.google.common.cache.Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(cacheConfig.getMaxCacheSize()).build();

        return cache;
    }
}
