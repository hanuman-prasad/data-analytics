package edu.elearning.cassandra.repository;

import com.datastax.driver.core.Session;
import edu.elearning.cassandra.repository.ops.CassandraOperations;
import edu.elearning.se.AsteriModel;

public class CassandraRepository implements Repository {


    private final CassandraOperations cassandraOps;


    public CassandraRepository(Session session) {
        cassandraOps = new CassandraOperations(session);
    }

    @Override
    public void save(AsteriModel model) {
        cassandraOps.insert(model);
    }
}
