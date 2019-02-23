package edu.elearning.translator;

import edu.elearning.se.User;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.getLocalDate;
import static edu.elearning.translator.TranslatorUtils.getValueFromInputMap;

public class UserTranslator implements Translator<User> {

    private static final Logger LOG = Logger.getLogger("UserTranslator");

    @Override
    public User translate(Map<String, String> map) throws TranslationException {

        LOG.info("Translation started..");

        User user = User.builder()
                .id(getValueFromInputMap(map, "id"))
                .displayName(getValueFromInputMap(map, "displayname"))
                .accountId(getValueFromInputMap(map, "accountid"))
                .location(getValueFromInputMap(map, "location"))
                .aboutMe(getValueFromInputMap(map, "aboutme"))
                .downVotes(getValueFromInputMap(map, "downvotes"))
                .profileImageUrl(getValueFromInputMap(map, "profileimageurl"))
                .reputation(getValueFromInputMap(map, "reputation"))
                .upVotes(getValueFromInputMap(map, "upvotes"))
                .views(getValueFromInputMap(map, "views"))
                .websiteUrl(getValueFromInputMap(map, "websiteurl"))
                .creationDate(getLocalDate(map, "creationdate"))
                .lastAccessDate(getLocalDate(map, "lastaccessdate"))
                .build();

        LOG.info("Translation completed for User : " + user.getId());

        return user;
    }
}
