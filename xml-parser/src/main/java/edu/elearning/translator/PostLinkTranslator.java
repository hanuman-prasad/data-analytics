package edu.elearning.translator;

import edu.elearning.se.LinkType;
import edu.elearning.se.PostLink;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.getEnumFromEnumIndex;
import static edu.elearning.translator.TranslatorUtils.getLocalDate;
import static edu.elearning.translator.TranslatorUtils.getValueFromInputMap;

public class PostLinkTranslator implements Translator<PostLink> {

    private static final Logger LOG = Logger.getLogger("PostLinkTranslator");

    @Override
    public PostLink translate(Map<String, String> map) throws TranslationException {

        LOG.info("Translation started for PostLink entity. Id : " + getValueFromInputMap(map, "id"));

        PostLink postLink = PostLink.builder()
                .id(getValueFromInputMap(map, "id"))
                .linkType(getEnumFromEnumIndex(LinkType.class,getValueFromInputMap(map, "linktypeid")))
                .postId(getValueFromInputMap(map, "postid"))
                .relatedPostId(getValueFromInputMap(map, "relatedpostid"))
                .creationDate(getLocalDate(map, "creationdate"))
                .build();

        LOG.info("Translation completed for PostLink. Id : " + postLink);
        return postLink;
    }
}
