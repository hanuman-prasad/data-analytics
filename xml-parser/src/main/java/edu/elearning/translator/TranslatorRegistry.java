package edu.elearning.translator;

import com.google.common.collect.ImmutableMap;
import edu.elearning.se.AsteriModel;

public interface TranslatorRegistry {

    ImmutableMap<String, Translator<? extends AsteriModel>> MODEL_REGISTRY = new ImmutableMap.Builder<String, Translator<? extends AsteriModel>>()
            .put("badges", new BadgeTranslator())
            .put("comments", new CommentTranslator())
            .put("posthistory", new PostHistoryTranslator())
            .put("postlinks", new PostLinkTranslator())
            .put("tags", new TagTranslator())
            .put("posts", new PostTranslator())
            .put("users", new UserTranslator())
            .build();

}
