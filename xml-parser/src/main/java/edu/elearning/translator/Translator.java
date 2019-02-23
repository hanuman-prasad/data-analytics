package edu.elearning.translator;

import java.util.Map;

public interface Translator<T> {
    T translate(Map<String, String> map) throws TranslationException;
}
