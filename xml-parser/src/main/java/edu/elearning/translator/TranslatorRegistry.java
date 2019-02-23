package edu.elearning.translator;

import com.google.common.collect.ImmutableMap;
import edu.elearning.se.AsteriModel;

public interface TranslatorRegistry {

    ImmutableMap<String, Translator<? extends AsteriModel>> MODEL_REGISTRY = new ImmutableMap.Builder<String, Translator<? extends AsteriModel>>()
            .put("badges", new BadgeTranslator())
            .put("posts", new PostTranslator())
            .put("users", new UserTranslator())
            .build();

}
