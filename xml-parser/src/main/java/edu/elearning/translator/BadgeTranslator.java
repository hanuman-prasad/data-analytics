package edu.elearning.translator;

import edu.elearning.se.Badge;
import edu.elearning.se.BadgeClass;
import edu.elearning.se.UserWebsite;

import java.util.Map;
import java.util.logging.Logger;

import static edu.elearning.translator.TranslatorUtils.getEnumFromString;
import static edu.elearning.translator.TranslatorUtils.getLocalDate;
import static edu.elearning.translator.TranslatorUtils.getValueFromInputMap;


public class BadgeTranslator implements Translator<Badge> {

    private static final Logger LOG = Logger.getLogger("BadgeTranslator");


    @Override
    public Badge translate(Map<String, String> map) throws TranslationException {

        LOG.info("Translation started for Badge entity. id : " + getValueFromInputMap(map, "id"));

        Badge badge = Badge.builder()
                .id(getValueFromInputMap(map, "id"))
                .name(getValueFromInputMap(map, "name"))
                .tagBased(getValueFromInputMap(map, "tagbased"))
                .userId(getValueFromInputMap(map, "userid"))
                .date(getLocalDate(map, "date"))
                .badgeClass(getBadgeClass(getValueFromInputMap(map, "class")))
                .userWebsite(getEnumFromString(UserWebsite.class, getValueFromInputMap(map,"se_website")))
                .build();


        LOG.info("Translation completed for Badge - " + badge.getId());
        return badge;
    }

    private BadgeClass getBadgeClass(String badgeclass) {

        int i;
        try {

            i = Integer.parseInt(badgeclass);
        } catch (NumberFormatException nfe) {
            return null;
        }


        return BadgeClass.values()[i - 1];
    }
}

