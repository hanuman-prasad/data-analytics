package edu.elearning.translator;

public class TranslationException extends Exception {

    public enum TranslationExceptionType {
        DATE_PARSING_EXCEPTION("Failed to parse date"),
        INTEGER_PARSE_EXCEPTION("Integer parse exception"),
        ENUM_NOT_FOUND("Enum constant is not defined"),
        EMPTY_KEY("Attribute name is empty in xml file"),
        EMPTY_VALUE("Attribute value is empty in xml file");

        private final String message;

        TranslationExceptionType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    private final TranslationExceptionType type;


    public TranslationException(TranslationExceptionType type) {
        this.type = type;
    }

    public TranslationExceptionType getType() {
        return type;
    }
}
