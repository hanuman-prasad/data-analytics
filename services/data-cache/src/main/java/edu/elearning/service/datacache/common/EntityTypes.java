package edu.elearning.service.datacache.common;

import com.google.common.collect.ImmutableMap;
import edu.elearning.se.*;

public final class EntityTypes {

    private static final ImmutableMap<String, Class<? extends AsteriModel>> ENTITIES_TYPE = ImmutableMap.<String, Class<? extends AsteriModel>>builder()
    .put("badge", Badge.class)
    .put("comment", Comment.class)
    .put("post", Post.class)
    .put("posthistory", PostHistory.class)
    .put("postlink", PostLink.class)
    .put("tag", Tag.class)
    .put("user", User.class)
    .put("vote", Vote.class)
    .build();

    public static Class<? extends AsteriModel> getEntityClass(String entity){
        return ENTITIES_TYPE.get(entity.toLowerCase());
    }
}
