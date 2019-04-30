package edu.elearning.cassandra.repository;

import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;

import java.util.List;

public interface Repository {

    void save(AsteriModel model);

    List<AsteriModel> query(UserWebsite website, Class<? extends AsteriModel> kClass, String paramName, String paramValue);
}
