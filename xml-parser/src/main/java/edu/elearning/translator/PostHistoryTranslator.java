package edu.elearning.translator;

import edu.elearning.se.PostHistory;
import edu.elearning.se.PostHistoryType;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.*;

public class PostHistoryTranslator implements Translator<PostHistory> {
    private static final Logger LOG = Logger.getLogger("PostHistoryTranslator");

    @Override
    public PostHistory translate(Map<String, String> map) throws TranslationException {

        LOG.info("Translation started for PostHistory entity. Id - " + getValueFromInputMap(map, "id"));

        PostHistory postHistory = PostHistory.builder()
                .id(getValueFromInputMap(map, "id"))
                .comment(getValueFromInputMap(map, "comment"))
                .postId(getValueFromInputMap(map, "postid"))
                .revisionGuId(getValueFromInputMap(map, "revisionguid"))
                .text(getValueFromInputMap(map, "text"))
                .userId(getValueFromInputMap(map, "userid"))
                .creationDate(getLocalDate(map, "creationdate"))
                .postHistoryType(getEnumFromEnumIndex(PostHistoryType.class, getValueFromInputMap(map, "posthistorytypeid")))
                .build();

        LOG.info("Translation completed for PostHistory entity. Id - " + postHistory.getId());

        return postHistory;
    }
}
