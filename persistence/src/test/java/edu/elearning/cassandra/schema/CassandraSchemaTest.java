package edu.elearning.cassandra.schema;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import edu.elearning.cassandra.connection.CassandraConnectionManager;
import edu.elearning.cassandra.serializer.AsteriModelSerializerImpl;
import edu.elearning.se.Badge;
import edu.elearning.se.UserWebsite;
import org.apache.cassandra.utils.UUIDGen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class CassandraSchemaTest {

    public static final String SEMI_COLON = ";";
    private KeyspaceSchema keyspaceSchema;
    private Session session;


    @Before
    public void connect() {
        CassandraConnectionManager client = new CassandraConnectionManager();
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


        session.execute("DROP KEYSPACE " + keyspaceName + SEMI_COLON);

    }

    @Test
    public void insert_test() {

        Badge badge = Badge.builder()
                .id("4444")
                .name("BRONZ")
                .userWebsite(UserWebsite.BEER)
                .tagBased("undefined")
                .build();

        PreparedStatement prepared = session.prepare("INSERT INTO local_entities.staxdata (key, payload) VALUES (?, ?)");

        UUID timeUUID = UUIDGen.getTimeUUID();
        AsteriModelSerializerImpl asteriModelSerializer = new AsteriModelSerializerImpl();
        ByteBuffer serialize = asteriModelSerializer.serialize(badge);
        BoundStatement bound = prepared.bind(timeUUID, serialize);
        session.execute(bound);

        ResultSet result = session.execute("SELECT * FROM local_entities.staxdata;");

        result.all()
                .stream()
                .forEach(r -> {
                    System.out.println(asteriModelSerializer.deserialize(r.getBytes("payload")));
                });

        System.out.println("-----");
    }

}