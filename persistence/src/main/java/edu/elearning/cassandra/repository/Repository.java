package edu.elearning.cassandra.repository;

import edu.elearning.se.AsteriModel;

public interface Repository {

    public void save(Class<? extends AsteriModel> typeClass, AsteriModel model);
}
