package edu.elearning.service.datacache.cache;

import edu.elearning.cassandra.repository.Repository;
import edu.elearning.se.UserWebsite;
import edu.elearning.service.datacache.common.EntityTypes;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

public class EntityCacheQuery implements CacheQuery {

    private final EntityCacheManager cacheManager;
    private final Repository repository;

    public EntityCacheQuery(EntityCacheManager cacheManager, Repository repository) {
        this.cacheManager = cacheManager;
        this.repository = repository;
    }


    @Override
    public List query(UserWebsite website, String entityClass, String entityId) {
        Cache cache = cacheManager.getEntityCaches(entityClass);

        List<UUID> key = repository.queryUUIds(website, EntityTypes.getEntityClass(entityClass), "id", entityId);

        return key.stream()
                .map(k -> cache.get(k))
                .collect(toList());
    }
}
