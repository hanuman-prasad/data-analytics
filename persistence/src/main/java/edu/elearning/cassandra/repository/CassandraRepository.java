package edu.elearning.cassandra.repository;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import edu.elearning.cassandra.repository.utils.CassandraUtil;
import edu.elearning.cassandra.serializer.AsteriModelSerializer;
import edu.elearning.se.AsteriModel;

import java.nio.ByteBuffer;

public class CassandraRepository implements Repository {

    private final Session session;
    private final AsteriModelSerializer modelSerializer;

    public CassandraRepository(Session session, AsteriModelSerializer modelSerializer) {
        this.session = session;
        this.modelSerializer = modelSerializer;
    }

    @Override
    public void save(Class<? extends AsteriModel> typeClass, AsteriModel model) {

        String className = typeClass.getName();
        ByteBuffer payload = modelSerializer.serialize(model);



        PreparedStatement preparedStatement = session.prepare("insert into staxdata (key, payload) values (?, ?)");
        String query = CassandraUtil.getPreparedStatement(typeClass, model);
    }
}
