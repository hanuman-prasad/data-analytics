package edu.elearning.translator;

import edu.elearning.TranslatorUtils;
import edu.elearning.se.Post;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

public class PostTranslator implements Translator<Post> {

    private static final Logger LOG = Logger.getLogger("BadgeTranslator");


    @Override
    public Post translate(Map<String, String> map) {

        LOG.info("Translation started for Post Id - " + getVal(map, "id"));

        Post post = Post.builder()

                .id(getVal(map, "id"))
                .postTypeId(getVal(map, "posttypeid"))
                .acceptedAnswerId(getVal(map, "acceptedanswerid"))
                .score(getVal(map, "score"))
                .viewCount(getVal(map, "viewcount"))
                .body(getVal(map, "body"))
                .ownerUserId(getVal(map, "owneruserid"))
                .lastEditorUserId(getVal(map, "lasteditoruserid"))
                .title(getVal(map, "title"))
                .tags(getVal(map, "tags"))
                .answerCount(getVal(map, "answercount"))
                .commentCount(getVal(map, "commentcount"))
                .favoriteCount(getVal(map, "favoritecount"))
                .creationDate(getDate(map, "creationdate"))
                .lastEditDate(getDate(map, "lasteditdate"))
                .lastActivityDate(getDate(map, "lastactivitydate"))
                .build();

        LOG.info("Translation completed for Post - " + post.getId());

        return post;
    }


    private LocalDateTime getDate(Map<String, String> map, String date) {
        return TranslatorUtils.getLocalDateTime(getVal(map, date));
    }

    private String getVal(Map<String, String> map, String key) {
        return TranslatorUtils.getValueFromInputMap(map, key);
    }

}
