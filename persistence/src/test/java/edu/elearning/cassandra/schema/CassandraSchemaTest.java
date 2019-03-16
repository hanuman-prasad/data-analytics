package edu.elearning.cassandra.schema;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import edu.elearning.cassandra.connection.CassandraConnector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class CassandraSchemaTest {

    public static final String SEMI_COLON = ";";
    private KeyspaceSchema keyspaceSchema;
    private Session session;


    @Before
    public void connect() {
        CassandraConnector client = new CassandraConnector();
        client.connect("127.0.0.1", 9042);
        this.session = client.getSession();
        keyspaceSchema = new KeyspaceSchema(session);
    }

    @After
    public void disConnect() {
        session.close();
    }

    @Test
    public void whenCreatingAKeyspace_thenCreated() {
        String keyspaceName = "test_keyspace";
        keyspaceSchema.createKeyspace(keyspaceName, "SimpleStrategy", 1);

        ResultSet result =
                session.execute("SELECT * FROM system_schema.keyspaces;");

        Optional<String> createdKeySpace = result.all()
                .stream()
                .filter(ks -> ks.getString(0).equals(keyspaceName.toLowerCase()))
                .map(ks -> ks.getString(0))
                .findAny();

        assertTrue(createdKeySpace.isPresent());
        assertTrue(keyspaceName.equals(createdKeySpace.get()));

        ResultSet dropKeyspace = session.execute("DROP KEYSPACE " + keyspaceName + SEMI_COLON);

        List<Row> all = dropKeyspace.all();
    }

}