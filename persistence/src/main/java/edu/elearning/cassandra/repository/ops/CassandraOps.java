package edu.elearning.cassandra.repository.ops;

import edu.elearning.se.AsteriModel;

public interface CassandraOps {
    default void insert(AsteriModel model){
        // no ops
    }
}
