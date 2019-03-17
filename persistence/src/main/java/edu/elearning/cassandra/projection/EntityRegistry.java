package edu.elearning.cassandra.projection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;

public interface EntityRegistry {

    Map<String, List<String>> projections =
            ImmutableMap.<String, List<String>>builder()
                    .put("Badge", ImmutableList.of("id", "name", "userId"))
                    .put("Comment", ImmutableList.of("id", "postId", "userId"))
                    .put("Post", ImmutableList.of("id", "tags", "ownerUserId"))
                    .put("PostLink", ImmutableList.of("id", "relatedPostId", "postId"))
                    .put("User", ImmutableList.of("id", "upVotes", "views"))
                    .put("Vote", ImmutableList.of("id", "postId"))
                    .build();
}
