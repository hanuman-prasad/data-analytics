package edu.elearning.service.datacache.cache;

import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;

import java.util.List;

public class EntityCacheManager {

    private final UserWebsite userWebsite;
    private final List<AsteriModel> entities;
    private final long maxCacheSize;

    private final CacheFactory cacheFactory;


    public EntityCacheManager(UserWebsite userWebsite, List<AsteriModel> entities, long maxCacheSize, CacheFactory cacheFactory) {
        this.userWebsite = userWebsite;
        this.entities = entities;
        this.maxCacheSize = maxCacheSize;
        this.cacheFactory = cacheFactory;
    }

    public void start(){
        entities.stream()
                .forEach(e ->{
                    cacheFactory.createCache(e, maxCacheSize);
                });
    }
}
