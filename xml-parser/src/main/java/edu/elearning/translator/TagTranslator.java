package edu.elearning.translator;

import edu.elearning.se.Tag;
import edu.elearning.se.UserWebsite;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.getEnumFromString;
import static edu.elearning.translator.TranslatorUtils.getValueFromInputMap;

public class TagTranslator implements Translator<Tag> {

    private static final Logger LOG = Logger.getLogger("TagTranslator");

    @Override
    public Tag translate(Map<String, String> map) throws TranslationException {

        LOG.info("Translation started for Tag entity. Id : " + getValueFromInputMap(map, "id"));

        Tag tag = Tag.builder()
                .id(getValueFromInputMap(map, "id"))
                .tagName(getValueFromInputMap(map, "tagname"))
                .count(getValueFromInputMap(map, "count"))
                .excerptPostId(getValueFromInputMap(map, "excerptpostid"))
                .wikiPostId(getValueFromInputMap(map, "wikipostid"))
                .userWebsite(getEnumFromString(UserWebsite.class, getValueFromInputMap(map,"se_website")))
                .build();


        LOG.info("Translation completed for Tag entity. Id : " + tag);

        return tag;
    }
}
