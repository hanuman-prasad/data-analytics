package edu.elearning.translator;

import edu.elearning.TranslatorUtils;
import edu.elearning.se.Badge;
import edu.elearning.se.BadgeClass;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

public class BadgeTranslator implements Translator<Badge> {

    private static final Logger LOG = Logger.getLogger("BadgeTranslator");


    @Override
    public Badge translate(Map<String, String> map) {

        LOG.info("Translation started..");

        Badge badge = Badge.builder()
                .id(getVal(map, "id"))
                .name(getVal(map, "name"))
                .tagBased(getVal(map, "tagbased"))
                .userId(getVal(map, "userid"))
                .date(getDate(map, "date"))
                .badgeClass(getBadgeClass(getVal(map, "class")))
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

    private LocalDateTime getDate(Map<String, String> map, String date) {
        return TranslatorUtils.getLocalDateTime(getVal(map, date));
    }

    private String getVal(Map<String, String> map, String key) {
        return TranslatorUtils.getValueFromInputMap(map, key);
    }
}

