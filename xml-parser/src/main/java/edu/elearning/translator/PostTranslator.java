package edu.elearning.translator;

import edu.elearning.se.Post;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.TranslatorUtils.getLocalDate;
import static edu.elearning.TranslatorUtils.getValueFromInputMap;

public class PostTranslator implements Translator<Post> {

    private static final Logger LOG = Logger.getLogger("BadgeTranslator");


    @Override
    public Post translate(Map<String, String> map) {

        LOG.info("Translation started for Post Id - " + getValueFromInputMap(map, "id"));

        Post post = Post.builder()

                .id(getValueFromInputMap(map, "id"))
                .postTypeId(getValueFromInputMap(map, "posttypeid"))
                .acceptedAnswerId(getValueFromInputMap(map, "acceptedanswerid"))
                .score(getValueFromInputMap(map, "score"))
                .viewCount(getValueFromInputMap(map, "viewcount"))
                .body(getValueFromInputMap(map, "body"))
                .ownerUserId(getValueFromInputMap(map, "owneruserid"))
                .lastEditorUserId(getValueFromInputMap(map, "lasteditoruserid"))
                .title(getValueFromInputMap(map, "title"))
                .tags(getValueFromInputMap(map, "tags"))
                .answerCount(getValueFromInputMap(map, "answercount"))
                .commentCount(getValueFromInputMap(map, "commentcount"))
                .favoriteCount(getValueFromInputMap(map, "favoritecount"))
                .creationDate(getLocalDate(map, "creationdate"))
                .lastEditDate(getLocalDate(map, "lasteditdate"))
                .lastActivityDate(getLocalDate(map, "lastactivitydate"))
                .build();

        LOG.info("Translation completed for Post - " + post.getId());

        return post;
    }
}
