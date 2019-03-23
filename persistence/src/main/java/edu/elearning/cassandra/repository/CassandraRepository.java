package edu.elearning.cassandra.repository;

import edu.elearning.cassandra.connection.CassandraConnectionFactory;
import edu.elearning.cassandra.repository.ops.CassandraOperations;
import edu.elearning.se.AsteriModel;

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
    public List<AsteriModel> query(Class<? extends AsteriModel> kClass, String paramName, String paramValue) {

        return cassandraOps.query(kClass, paramName, paramValue);
    }
}
