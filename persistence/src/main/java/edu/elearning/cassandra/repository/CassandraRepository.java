package edu.elearning.cassandra.repository;

import edu.elearning.cassandra.connection.CassandraConnectionFactory;
import edu.elearning.cassandra.repository.ops.CassandraOperations;
import edu.elearning.se.AsteriModel;

/**
 * client should use this class's instance to interact with cassandra
 */
public class CassandraRepository implements Repository {


    private final CassandraOperations cassandraOps;


    public CassandraRepository() {
        cassandraOps = new CassandraOperations(CassandraConnectionFactory.getSession());
    }

    @Override
    public void save(AsteriModel model) {
        cassandraOps.insert(model);
    }
}
