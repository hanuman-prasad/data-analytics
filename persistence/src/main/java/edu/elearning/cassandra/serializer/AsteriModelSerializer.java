package edu.elearning.cassandra.serializer;

import edu.elearning.se.AsteriModel;

import java.nio.ByteBuffer;

public interface AsteriModelSerializer {
    ByteBuffer serialize(AsteriModel entity);
    Object deserialize(ByteBuffer blob);
}
