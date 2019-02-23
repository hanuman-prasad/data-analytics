package edu.elearning.translator;

import edu.elearning.TranslatorUtils;
import edu.elearning.se.User;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

public class UserTranslator implements Translator<User> {

    private static final Logger LOG = Logger.getLogger("UserTranslator");

    @Override
    public User translate(Map<String, String> map) {

        LOG.info("Translation started..");

        User user = User.builder()
                .id(getVal(map, "id"))
                .displayName(getVal(map, "displayname"))
                .accountId(getVal(map, "accountid"))
                .location(getVal(map, "location"))
                .aboutMe(getVal(map, "aboutme"))
                .downVotes(getVal(map, "downvotes"))
                .profileImageUrl(getVal(map, "profileimageurl"))
                .reputation(getVal(map, "reputation"))
                .upVotes(getVal(map, "upvotes"))
                .views(getVal(map, "views"))
                .websiteUrl(getVal(map, "websiteurl"))
                .creationDate(getDate(map, "creationdate"))
                .lastAccessDate(getDate(map, "lastaccessdate"))
                .build();

        LOG.info("Translation completed for User : " + user.getId());

        return user;
    }

    private LocalDateTime getDate(Map<String, String> map, String date) {
        return TranslatorUtils.getLocalDateTime(getVal(map, date));
    }

    private String getVal(Map<String, String> map, String key) {
        return TranslatorUtils.getValueFromInputMap(map, key);
    }
}
