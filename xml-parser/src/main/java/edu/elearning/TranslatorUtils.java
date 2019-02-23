package edu.elearning;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Logger;

public class TranslatorUtils {
    private static final Logger LOG = Logger.getLogger("TranslatorUtils");

    public static String getValueFromInputMap(Map<String, String> map, String key) {

        checkForNull(map, key);

        return map.get(key);
    }

    private static void checkForNull(Map<String, String> map, String key) {
        if (map == null || StringUtils.isBlank(key)) {
            throw new NullPointerException("Input map or kry is empty!");
        }
    }

    public static LocalDateTime getLocalDateTime(String date) {

        if (StringUtils.isBlank(date)) {
            LOG.warning("Date is empty.");
            return null;
        }

        LocalDateTime localDateTime = LocalDateTime.parse(date);
        return localDateTime;

    }
}
