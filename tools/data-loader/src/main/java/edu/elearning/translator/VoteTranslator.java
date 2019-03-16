package edu.elearning.translator;

import edu.elearning.se.UserWebsite;
import edu.elearning.se.Vote;
import edu.elearning.se.VoteType;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.*;

public class VoteTranslator implements Translator<Vote> {

    private static final Logger LOG = Logger.getLogger("VoteTranslator");

    @Override
    public Vote translate(Map<String, String> map) throws TranslationException {
        LOG.info("Translation started for Vote entity. Id : " + getValueFromInputMap(map, "id"));

        Vote vote = Vote.builder()
                .id(getIdWithWebsitePrefix(map))
                .postId(getValueFromInputMap(map, "postid"))
                .voteTypeId(getEnumFromEnumIndex(VoteType.class, getValueFromInputMap(map, "votetypeid")))
                .creationDate(getLocalDate(map, "creationdate"))
                .userWebsite(getEnumFromString(UserWebsite.class, getValueFromInputMap(map,"se_website")))
                .build();

        LOG.info("Translation completed for Tag entity. Id : " + vote);

        return vote;
    }
}
