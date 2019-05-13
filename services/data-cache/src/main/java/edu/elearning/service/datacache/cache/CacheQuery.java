package edu.elearning.service.datacache.cache;

import edu.elearning.se.UserWebsite;

import java.util.List;

public interface CacheQuery {

  List query(UserWebsite website, String entityClass, String entityId);
}
