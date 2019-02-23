package edu.elearning.translator;

import edu.elearning.se.AsteriModel;

public class TranslationResult {

    private final AsteriModel asteriModel;
    private final TranslationException exception;

    public TranslationResult(AsteriModel asteriModel, TranslationException exception) {
        this.asteriModel = asteriModel;
        this.exception = exception;
    }

    public AsteriModel getAsteriModel() {
        return asteriModel;
    }

    public TranslationException getException() {
        return exception;
    }
}
