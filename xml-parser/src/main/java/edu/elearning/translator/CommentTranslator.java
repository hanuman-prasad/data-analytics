package edu.elearning.translator;

import edu.elearning.se.Comment;
import edu.elearning.se.UserWebsite;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.getEnumFromString;
import static edu.elearning.translator.TranslatorUtils.getLocalDate;
import static edu.elearning.translator.TranslatorUtils.getValueFromInputMap;

public class CommentTranslator implements Translator<Comment> {

    private static final Logger LOG = Logger.getLogger("CommentTranslator");

    @Override
    public Comment translate(Map<String, String> map) throws TranslationException {

        LOG.info("Translation started for Comment entity. id : " + getValueFromInputMap(map, "id"));

        Comment comment = Comment.builder()
                .id(getValueFromInputMap(map, "id"))
                .postId(getValueFromInputMap(map, "postid"))
                .score(getValueFromInputMap(map, "score"))
                .text(getValueFromInputMap(map, "text"))
                .creationDate(getLocalDate(map, "creationdate"))
                .userId(getValueFromInputMap(map, "userid"))
                .userWebsite(getEnumFromString(UserWebsite.class, getValueFromInputMap(map,"se_website")))
                .build();

        LOG.info("Translation completed for Tag entity. Id : " + comment);

        return comment;
    }
}
