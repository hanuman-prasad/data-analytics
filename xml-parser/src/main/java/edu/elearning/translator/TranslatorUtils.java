package edu.elearning.translator;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TranslatorUtils {
    private static final Logger LOG = Logger.getLogger("TranslatorUtils");


    public static String getValueFromInputMap(Map<String, String> map, String key) throws TranslationException {
        checkKeyForNullOrEmpty(key);
        String val = map.get(key);

        LOG.log(Level.FINEST, "Key : " + key + " Value : " + val);

        return val;
    }


    public static LocalDateTime getLocalDate(Map<String, String> map, String dateName) throws TranslationException {

        checkKeyForNullOrEmpty(dateName);

        String dateString = getValueFromInputMap(map, dateName);

        if (StringUtils.isBlank(dateString)) {
            return null;
        }

        LocalDateTime localDateTime;
        try {

            localDateTime = LocalDateTime.parse(dateString);
        } catch (DateTimeParseException dte) {
            LOG.warning("DateTimeParseException while parsing date : " + dateName + " value : " + dateString);
            throw new TranslationException(TranslationException.TranslationExceptionType.DATE_PARSING_EXCEPTION);
        }

        LOG.log(Level.FINEST, "Date name : " + dateName + ", Date value : " + dateString);

        return localDateTime;

    }


    public static <T> T getEnumFromEnumIndex(Class<T> enumClass, String index) throws TranslationException {

        if (StringUtils.isBlank(index)) {
            return null;
        }

        int i;

        try {
            i = Integer.parseInt(index);
        } catch (NumberFormatException nfe) {
            LOG.warning("Number format exception");
            throw new TranslationException(TranslationException.TranslationExceptionType.INTEGER_PARSE_EXCEPTION);
        }

        T[] enumConstants = enumClass.getEnumConstants();

        if (i >= enumConstants.length) {
            throw new TranslationException(TranslationException.TranslationExceptionType.ENUM_NOT_FOUND);
        }

        return enumConstants[i - 1];
    }

    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
            }
        }
        return null;
    }

    public static String removeHtmlTag(String inputString) {

        inputString = inputString.replaceAll("&lt;", "<");
        inputString = inputString.replaceAll("&gt;", ">");
        inputString = inputString.replaceAll("&quot;", "\"");
        inputString = inputString.replaceAll("&#xA;", "\n");
        inputString = inputString.replaceAll("</{0,1}[a-z]{1,8}>", "");

        return inputString;
    }

    private static void checkKeyForNullOrEmpty(String str) throws TranslationException {
        if (StringUtils.isBlank(str)) {
            LOG.warning("Key is empty!");
            throw new TranslationException(TranslationException.TranslationExceptionType.EMPTY_KEY);
        }
    }
}
