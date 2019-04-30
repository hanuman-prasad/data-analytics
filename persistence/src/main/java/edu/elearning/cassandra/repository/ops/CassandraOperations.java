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
import edu.elearning.se.UserWebsite;
import org.apache.cassandra.utils.UUIDGen;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class CassandraOperations {

    private static final Session session = CassandraConnectionFactory.getSession();

    private static final String INSERT_ENTITIES = "INSERT INTO local_entities.entities (key, entity_id, payload) VALUES (?, ?, ?)";
    private static final String INSERT_SUBENTITIES_PROJECTION = "INSERT INTO local_entities.subentities_projection (website, entity_class, entity_subclass, subentity_value, key) values (?, ?, ?, ?, ?)";
    private static final String INSERT_PROJECTION = "INSERT INTO local_entities.entities_projection_1 (website, entity_class, index_name, index_value, entity_id, key) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String INSERT_IDS = "INSERT INTO local_entities.entities_by_ids (website, entity_class, entity_id) VALUES (?, ?, ?)";
    private static final String SELECT_ENTITIES = "SELECT payload FROM local_entities.entities WHERE key = ?";
    private static final String SELECT_PROJECTION = "SELECT key FROM local_entities.entities_projection_1 WHERE website = ? AND entity_class = ? AND index_name = ? AND index_value = ?";


    private static PreparedStatement insertEntitiesPrepared = session.prepare(INSERT_ENTITIES);
    private static PreparedStatement insertSubEntitiesProjectionPrepared = session.prepare(INSERT_SUBENTITIES_PROJECTION);
    private static PreparedStatement insertProjectionPrepared = session.prepare(INSERT_PROJECTION);
    private static PreparedStatement insertIdsPrepared = session.prepare(INSERT_IDS);
    private static PreparedStatement selectEntitiesPrepared = session.prepare(SELECT_ENTITIES);
    private static PreparedStatement selectProjectionPreapared = session.prepare(SELECT_PROJECTION);


    public void insert(AsteriModel model) {
        UUID key = insertInEntitiesTable(model);
        insertIntoIdsTable(model);
        insertInProjectionTable(model, key);
    }


    public List<AsteriModel> query(UserWebsite website, Class<? extends AsteriModel> modelClass, String key, String value) {
        Preconditions.checkNotNull(modelClass, "Entity class can't be null");
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "Parameter name can't be empty");
        Preconditions.checkArgument(StringUtils.isNotBlank(key), "Parameter value can't be empty");
        String websiteName = website.name();
        String entityClass = modelClass.getSimpleName();


        List<UUID> uuids = queryPayloadKeyFromProjectionTable(websiteName, key, value, entityClass);


        return uuids.stream()
                .map(this::queryActualModelFromEntitiesTable)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<AsteriModel> queryActualModelFromEntitiesTable(UUID uuid) {
        BoundStatement bindStaxdata = selectEntitiesPrepared.bind(uuid);
        ResultSet resultSet = session.execute(bindStaxdata);
        return resultSet.all()
                .stream()
                .map(row -> row.getBytes("payload"))
                .map(AsteriModelSerializer::deserialize)
                .findFirst();
    }

    private List<UUID> queryPayloadKeyFromProjectionTable(String website, String key, String value, String entityClass) {
        BoundStatement bind = selectProjectionPreapared.bind(website, entityClass, key, value);
        ResultSet execute = session.execute(bind);

        return execute.all().stream().map(r -> r.getUUID("key")).collect(toList());
    }


    private UUID insertInEntitiesTable(AsteriModel model) {

        UUID key = UUIDGen.getTimeUUID();

        ByteBuffer byteBuffer = AsteriModelSerializer.serialize(model);
        BoundStatement boundStatement = insertEntitiesPrepared.bind(key, FieldManipulator.getFieldValue(model, "id"), byteBuffer);
        session.execute(boundStatement);

        insertInSubEntitiesTable(model, key);

        return key;
    }

    private void insertIntoIdsTable(AsteriModel model) {

        String website = model.getUserWebsite().name();
        String entity_class = model.getClass().getSimpleName();
        String id = model.getId();

        BoundStatement boundStatement = insertIdsPrepared.bind(website, entity_class, id);
        session.execute(boundStatement);

    }

    private void insertInSubEntitiesTable(AsteriModel model, UUID key) {
        if (model instanceof Post) {
            String entity_class = Post.class.getSimpleName();
            String userWebsite = model.getUserWebsite().name();
            List<String> tags = ((Post) model).getTags();
            if (tags != null) tags.forEach(tag -> {
                insertIntoEntitiesModelTable(userWebsite, entity_class, "tag", tag, key);
            });
        }

    }

    private void insertIntoEntitiesModelTable(String userWebsite, String entity_class, String name, String value, UUID key) {
        BoundStatement bind = insertSubEntitiesProjectionPrepared.bind(userWebsite, entity_class, name, value, key);
        session.execute(bind);
    }

    private void insertInProjectionTable(AsteriModel model, UUID key) {

        String modelName = model.getClass().getSimpleName();

        List<String> projections = getProjectionForModel(modelName);

        Set<BoundStatement> statements = new HashSet<>(projections.size());

        prepareStatements(modelName, model, key, statements, "id");

        projections
                .forEach(p -> {
                            prepareStatements(modelName, model, key, statements, p);
                        }
                );

        statements.forEach(stmt -> session.execute(stmt));
    }

    private void prepareStatements(String modelName, AsteriModel model, UUID key, Set<BoundStatement> statements, String p) {
        String userWebsite = model.getUserWebsite().name();
        Object indexValue = FieldManipulator.getFieldValue(model, p);
        if (indexValue == null) {
            return;
        }

        if (indexValue instanceof List) {
            for (String val : (List<String>) indexValue) {
                BoundStatement bind = insertProjectionPrepared.bind(userWebsite, modelName, p, val, model.getId(), key);
                statements.add(bind);
            }
        } else {
            BoundStatement bind = insertProjectionPrepared.bind(userWebsite, modelName, p, indexValue, model.getId(), key);
            statements.add(bind);
        }

    }

    private List<String> getProjectionForModel(String model) {

        return EntityRegistry.projections.getOrDefault(model, Collections.emptyList());

    }
}
