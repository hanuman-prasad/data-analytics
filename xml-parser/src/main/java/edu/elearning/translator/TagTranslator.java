package edu.elearning.translator;

import edu.elearning.se.Tag;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.TranslatorUtils.getValueFromInputMap;

public class TagTranslator implements Translator<Tag> {

    private static final Logger LOG = Logger.getLogger("PostLinkTranslator");

    @Override
    public Tag translate(Map<String, String> map) {

        LOG.info("Translation started for Tag entity. Id : " + getValueFromInputMap(map, "id"));
        Tag tag = Tag.builder()
                .id(getValueFromInputMap(map, "id"))
                .tagName(getValueFromInputMap(map, "tagname"))
                .count(getValueFromInputMap(map, "count"))
                .excerptPostId(getValueFromInputMap(map, "excerptpostid"))
                .wikiPostId(getValueFromInputMap(map, "wikipostid"))
                .build();


        LOG.info("Translation completed for PostLink. Id : " + tag);

        return tag;
    }
}
