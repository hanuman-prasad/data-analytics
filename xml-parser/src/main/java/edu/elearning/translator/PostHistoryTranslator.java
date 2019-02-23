package edu.elearning.translator;

import edu.elearning.se.PostHistory;
import edu.elearning.se.PostHistoryTypeId;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.TranslatorUtils.getEnum;
import static edu.elearning.TranslatorUtils.getLocalDate;
import static edu.elearning.TranslatorUtils.getValueFromInputMap;

public class PostHistoryTranslator implements Translator<PostHistory> {
    private static final Logger LOG = Logger.getLogger("PostHistoryTranslator");
    @Override
    public PostHistory translate(Map<String, String> map) {

        LOG.info("Translation started for PostHistory entity. Id - " + getValueFromInputMap(map, "id"));

        PostHistory postHistory = PostHistory.builder()
                .id(getValueFromInputMap(map, "id"))
                .comment(getValueFromInputMap(map, "comment"))
                .postId(getValueFromInputMap(map,"postid"))
                .revisionGuId(getValueFromInputMap(map, "revisionguid"))
                .text(getValueFromInputMap(map, "text"))
                .userId(getValueFromInputMap(map, "userid"))
                .creationDate(getLocalDate(map,"creationdate"))
                .postHistoryTypeId(getEnum(PostHistoryTypeId.class, getValueFromInputMap(map, "posthistorytypeid")))
                .build();

        LOG.info("Translation completed for PostHistory entity. Id - " + postHistory.getId());

        return postHistory;
    }
}
