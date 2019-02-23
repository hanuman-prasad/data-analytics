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

        String dateValue = getValueFromInputMap(map, dateName);

        checkValueForNullOrEmpty(dateValue);

        LocalDateTime localDateTime;
        try {

            localDateTime = LocalDateTime.parse(dateValue);
        } catch (DateTimeParseException dte) {
            LOG.warning("DateTimeParseException while parsing date : " + dateName + " value : " + dateValue);
            throw new TranslationException(TranslationException.TranslationExceptionType.DATE_PARSING_EXCEPTION);
        }

        LOG.log(Level.FINEST, "Date name : " + dateName + ", Date value : " + dateValue);

        return localDateTime;

    }


    public static <T> T getEnum(Class<T> enumClass, String index) throws TranslationException {

        checkValueForNullOrEmpty(index);

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

    private static void checkKeyForNullOrEmpty(String str) throws TranslationException {
        if (StringUtils.isBlank(str)) {
            LOG.warning("Key is empty!");
            throw new TranslationException(TranslationException.TranslationExceptionType.EMPTY_KEY);
        }
    }

    private static void checkValueForNullOrEmpty(String str) throws TranslationException {
        if (StringUtils.isBlank(str)) {
            LOG.warning("Key is empty!");
            throw new TranslationException(TranslationException.TranslationExceptionType.EMPTY_VALUE);
        }
    }
}
