package edu.elearning;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TranslatorUtils {
    private static final Logger LOG = Logger.getLogger("TranslatorUtils");


    public static String getValueFromInputMap(Map<String, String> map, String key) {
        checkForNull(map, key);
        String val = map.get(key);

        LOG.log(Level.OFF, "Key : " + key + " Value : " + val);

        return val;
    }


    public static LocalDateTime getLocalDate(Map<String, String> map, String dateName) {

        checkForNull(map, dateName);

        String dateValue = getValueFromInputMap(map, dateName);

        if (StringUtils.isBlank(dateValue)) {
            LOG.warning("Date is empty.");
            return null;
        }

        LocalDateTime localDateTime = LocalDateTime.parse(dateValue);

        LOG.log(Level.OFF, "Date name : " + dateName + ", Date value : " + dateValue);

        return localDateTime;

    }

    private static void checkForNull(Map<String, String> map, String key) {
        if (map == null || StringUtils.isBlank(key)) {
            throw new NullPointerException("Input map or kry is empty!");
        }
    }
}
