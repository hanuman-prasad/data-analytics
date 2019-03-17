package edu.elearning.cassandra.projection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public interface EntityRegistry {

    Map<String, List<String>> projections =
            ImmutableMap.<String, List<String>>builder()
                    .put("User", ImmutableList.of("id", "upVotes", "views"))
                    .build();
}
