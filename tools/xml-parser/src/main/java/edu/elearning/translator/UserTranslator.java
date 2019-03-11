package edu.elearning.translator;

import edu.elearning.se.User;
import edu.elearning.se.UserWebsite;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.*;

public class UserTranslator implements Translator<User> {

    private static final Logger LOG = Logger.getLogger("UserTranslator");

    @Override
    public User translate(Map<String, String> map) throws TranslationException {

        LOG.info("Translation started..");

        User user = User.builder()
                .id(getIdWithWebsitePrefix(map))
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
                .userWebsite(getEnumFromString(UserWebsite.class, getValueFromInputMap(map,"se_website")))
                .build();

        LOG.info("Translation completed for User : " + user.getId());

        return user;
    }
}
