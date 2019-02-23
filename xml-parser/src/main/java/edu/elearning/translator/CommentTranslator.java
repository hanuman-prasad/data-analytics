package edu.elearning.translator;

import edu.elearning.se.Comment;

import java.util.Map;

import static edu.elearning.TranslatorUtils.getLocalDate;
import static edu.elearning.TranslatorUtils.getValueFromInputMap;

public class CommentTranslator implements Translator<Comment> {
    @Override
    public Comment translate(Map<String, String> map) {

        Comment comment = Comment.builder()
                .id(getValueFromInputMap(map, "id"))
                .postId(getValueFromInputMap(map, "postid"))
                .score(getValueFromInputMap(map, "score"))
                .text(getValueFromInputMap(map, "text"))
                .creationDate(getLocalDate(map, "creationdate"))
                .userId(getValueFromInputMap(map, "userid"))
                .build();


        return comment;
    }
}
