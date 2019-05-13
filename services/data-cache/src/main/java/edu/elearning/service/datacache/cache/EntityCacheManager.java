package edu.elearning.service.datacache.cache;

import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityCacheManager {

    private final UserWebsite userWebsite;
    private final List<AsteriModel> entities;

    private final CacheFactory cacheFactory;

    private final Map<String, Cache> entityCaches = new HashMap<>();


    public EntityCacheManager(UserWebsite userWebsite, List<AsteriModel> entities, CacheFactory cacheFactory) {
        this.userWebsite = userWebsite;
        this.entities = entities;
        this.cacheFactory = cacheFactory;
    }

    public void start() {
        entities.stream()
                .forEach(e -> {
                    Class<? extends AsteriModel> eClass = e.getClass();
                    String cacheName = eClass.getSimpleName();

                    entityCaches.put(cacheName,
                            cacheFactory.createCache(eClass, userWebsite));
                });
    }

    public Cache getEntityCaches(String entityClass) {
        return entityCaches.get(entityClass);
    }
}
