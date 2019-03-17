package edu.elearning.cassandra.repository.ops;

import com.datastax.driver.core.Session;
import edu.elearning.se.AsteriModel;

public interface CassandraOps {
    default void insert(Class<? extends AsteriModel> kClass, AsteriModel model){
        // no ops
    }
}
