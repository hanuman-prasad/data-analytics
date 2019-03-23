package edu.elearning.cassandra.repository;

import edu.elearning.se.AsteriModel;

import java.util.List;

public interface Repository {

    void save(AsteriModel model);

    List<AsteriModel> query(Class<? extends AsteriModel> kClass, String paramName, String paramValue);
}
