package edu.elearning.cassandra.repository;

import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;

import java.util.List;
import java.util.UUID;

public interface Repository {

    void save(AsteriModel model);

    List<AsteriModel> query(UserWebsite website, Class<? extends AsteriModel> modelClass, String paramName, String paramValue);

    List<UUID> queryUUIds(UserWebsite website, Class<? extends AsteriModel> modelClass, String paramName, String paramValue);

    List<String> queryIds(UserWebsite website, Class<? extends AsteriModel> modelClass);

    void closeConnection();
}
