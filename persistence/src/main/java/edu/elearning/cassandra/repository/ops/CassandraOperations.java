package edu.elearning.cassandra.repository.ops;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.google.common.base.Preconditions;
import edu.elearning.cassandra.connection.CassandraConnectionFactory;
import edu.elearning.cassandra.projection.EntityRegistry;
import edu.elearning.cassandra.repository.utils.FieldManipulator;
import edu.elearning.cassandra.serializer.AsteriModelSerializer;
import edu.elearning.se.AsteriModel;
import edu.elearning.se.Post;
import org.apache.cassandra.utils.UUIDGen;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CassandraOperations {

    private static final Session session = CassandraConnectionFactory.getSession();

    private static final String INSERT_ENTITIES = "INSERT INTO local_entities.entities (key, entity_id, payload) VALUES (?, ?, ?)";
    private static final String INSERT_ENTITIES_MODEL = "INSERT INTO local_entities.entities_model (website, entity_class, name, value, key) values (?, ?, ?, ?, ?)";
    private static final String INSERT_PROJECTION = "INSERT INTO local_entities.entities_projection_1 (entity_class, index_name, index_value, entity_id, key) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ENTITIES = "SELECT payload FROM local_entities.entities WHERE key = ?";
    private static final String SELECT_PROJECTION = "SELECT key FROM local_entities.entities_projection_1 WHERE entity_class = ? AND index_name = ? AND index_value = ?";


    private static PreparedStatement insertEntitiesPrepared = session.prepare(INSERT_ENTITIES);
    private static PreparedStatement insertEntitiesModelPrepared = session.prepare(INSERT_ENTITIES_MODEL);
    private static PreparedStatement insetProjectionPrepared = session.prepare(INSERT_PROJECTION);
    private static PreparedStatement selectEntitiesPrepared = session.prepare(SELECT_ENTITIES);
    private static PreparedStatement selectProjectionPreapared = session.prepare(SELECT_PROJECTION);


    public void insert(AsteriModel model) {
        UUID key = insertInEntitiesTable(model);
        insertInProjectionTable(model, key);
    }


    public List<AsteriModel> query(Class<? extends AsteriModel> modelClass, String key, String value) {
        Preconditions.checkNotNull(modelClass, "Entity class can't be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "Parameter name can't be empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "Parameter value can't be empty");
        String entityClass = modelClass.getSimpleName();


        List<UUID> uuids = queryPayloadKeyFromProjectionTable(key, value, entityClass);


        List<AsteriModel> data = uuids.stream()
                .map(this::queryActualModelFromEntitiesTable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return data;
    }

    private Optional<AsteriModel> queryActualModelFromEntitiesTable(UUID uuid) {
        BoundStatement bindStaxdata = selectEntitiesPrepared.bind(uuid);
        ResultSet resultSet = session.execute(bindStaxdata);
        Optional<AsteriModel> model = resultSet.all()
                .stream()
                .map(row -> row.getBytes("payload"))
                .map(AsteriModelSerializer::deserialize)
                .findFirst();
        return model;
    }

    private List<UUID> queryPayloadKeyFromProjectionTable(String key, String value, String entityClass) {
        BoundStatement bind = selectProjectionPreapared.bind(entityClass, key, value);
        ResultSet execute = session.execute(bind);

        return execute.all().stream().map(r -> r.getUUID("key")).collect(toList());
    }


    private UUID insertInEntitiesTable(AsteriModel model) {

        UUID key = UUIDGen.getTimeUUID();

        ByteBuffer byteBuffer = AsteriModelSerializer.serialize(model);
        BoundStatement boundStatement = insertEntitiesPrepared.bind(key, FieldManipulator.getFieldValue(model, "id"), byteBuffer);
        session.execute(boundStatement);

        insertInEntitiesPostTags(model, key);

        return key;
    }

    private void insertInEntitiesPostTags(AsteriModel model, UUID key) {
        if (model instanceof Post) {
            String entity_class = Post.class.getSimpleName();
            String userWebsite = ((Post) model).getUserWebsite().name();
            List<String> tags = ((Post) model).getTags();
            tags.stream().forEach(tag -> {
                insertIntoEntitiesModelTable(userWebsite, entity_class, "tag", tag, key);
            });
        }

    }

    private void insertIntoEntitiesModelTable(String userWebsite, String entity_class, String name, String value, UUID key) {
        BoundStatement bind = insertEntitiesModelPrepared.bind(userWebsite, entity_class, name, value, key);
        session.execute(bind);
    }

    private void insertInProjectionTable(AsteriModel model, UUID key) {

        String modelName = model.getClass().getSimpleName();

        List<String> projections = getProjectionForModel(modelName);

        Set<BoundStatement> statements = new HashSet<>(projections.size());

        prepareStatements(modelName, model, key, statements, "id");

        projections.stream()
                .forEach(p -> {
                            prepareStatements(modelName, model, key, statements, p);
                        }
                );

        statements.stream().forEach(stmt -> session.execute(stmt));
    }

    private void prepareStatements(String modelName, AsteriModel model, UUID key, Set<BoundStatement> statements, String p) {
        Object indexValue = FieldManipulator.getFieldValue(model, p);
        if (indexValue == null) {
            return;
        }

        if (indexValue instanceof List) {
            for (String val : (List<String>) indexValue) {
                BoundStatement bind = insetProjectionPrepared.bind(modelName, p, val, model.getId(), key);
                statements.add(bind);
            }
        } else {
            BoundStatement bind = insetProjectionPrepared.bind(modelName, p, indexValue, model.getId(), key);
            statements.add(bind);
        }

    }

    private List<String> getProjectionForModel(String model) {

        return EntityRegistry.projections.getOrDefault(model, Collections.emptyList());

    }
}
