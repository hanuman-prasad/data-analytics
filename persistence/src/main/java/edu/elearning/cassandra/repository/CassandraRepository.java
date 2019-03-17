package edu.elearning.cassandra.repository;

import com.datastax.driver.core.Session;
import edu.elearning.cassandra.repository.ops.CassandraInsertOps;
import edu.elearning.cassandra.repository.ops.CassandraOps;
import edu.elearning.se.AsteriModel;

public class CassandraRepository implements Repository {


    private final CassandraOps insert;


    public CassandraRepository(Session session) {
        insert = new CassandraInsertOps(session);
    }

    @Override
    public void save(Class<? extends AsteriModel> typeClass, AsteriModel model) {

        insert.insert(typeClass, model);
    }
}
