package edu.elearning.cassandra.repository;

import edu.elearning.cassandra.repository.ops.CassandraOperations;
import edu.elearning.se.AsteriModel;
import edu.elearning.se.UserWebsite;

import java.util.List;

/**
 * client should use this class's instance to interact with cassandra
 */
public class CassandraRepository implements Repository {


    private final CassandraOperations cassandraOps;


    public CassandraRepository() {
        cassandraOps = new CassandraOperations();
    }

    @Override
    public void save(AsteriModel model) {
        cassandraOps.insert(model);
    }

    @Override
    public List<AsteriModel> query(UserWebsite website, Class<? extends AsteriModel> modelClass, String paramName, String paramValue) {

        return cassandraOps.query(website, modelClass, paramName, paramValue);
    }

    @Override
    public List<String> queryIds(UserWebsite website, Class<? extends AsteriModel> modelClass) {
        return cassandraOps.queryIds(website, modelClass);
    }
}
