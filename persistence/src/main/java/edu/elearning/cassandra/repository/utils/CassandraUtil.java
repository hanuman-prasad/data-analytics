package edu.elearning.cassandra.repository.utils;

import edu.elearning.se.AsteriModel;
import org.apache.cassandra.utils.UUIDGen;

import java.util.Set;
import java.util.UUID;

public class CassandraUtil {
    public static String getPreparedStatement(Class<? extends AsteriModel> typeClass, AsteriModel model) {
        StringBuilder query = new StringBuilder();
        query
                .append(Constants.INSERT_INTO + "my_keyspace" + Constants.DOT + "staxdata" + Constants.OPEN_BRACE)
                .append("key, payload")
                .append(Constants.CLOSING_BRACE)
                .append("values")
                .append(Constants.OPEN_BRACE)
                .append(UUIDGen.getTimeUUID())
                .append(Constants.COMMA);



        return null;
    }
}
