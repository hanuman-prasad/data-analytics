package edu.elearning.se;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDateTime;

@Builder
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Comment {

    @NonNull
    @EqualsAndHashCode.Include
    private String id;
    private String postId;
    private String score;
    private String text;
    private LocalDateTime creationDate;
    private String userId;
}
