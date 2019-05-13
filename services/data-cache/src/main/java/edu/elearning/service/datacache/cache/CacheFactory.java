package edu.elearning.service.datacache.cache;

import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;

public interface CacheFactory {

    Cache createCache(Class<? extends AsteriModel> entityClass, UserWebsite website);
}
