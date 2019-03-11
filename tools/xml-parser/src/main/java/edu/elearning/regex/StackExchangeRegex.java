package edu.elearning.regex;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackExchangeRegex {

    private static final Pattern keyPattern = Pattern.compile("\\w+=\"");
    private static final Pattern valuePattern = Pattern.compile("\".*?\"");

    public static Map<String, String> parseString(String str) {

        if (!str.contains("<row")) {
            return Collections.emptyMap();
        }

        Map<String, String> map = new HashMap<>();

        Matcher keyMatcher = keyPattern.matcher(str);
        Matcher valueMatcher = valuePattern.matcher(str);

        while (keyMatcher.find() && valueMatcher.find()) {
            String key = keyMatcher.group();
            key = key.substring(0, keyMatcher.group().length() - 2);
            String value = valueMatcher.group();
            value = value.substring(1, value.length() - 1);
            map.put(key.toLowerCase(), value);
        }
        return map;
    }
}
